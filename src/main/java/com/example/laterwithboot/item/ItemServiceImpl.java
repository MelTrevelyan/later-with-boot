package com.example.laterwithboot.item;

import com.example.laterwithboot.exception.ItemNotFoundException;
import com.example.laterwithboot.item.enums.ItemContentType;
import com.example.laterwithboot.item.enums.ItemSorting;
import com.example.laterwithboot.item.enums.ItemState;
import com.example.laterwithboot.item.metadata.UrlMetadata;
import com.example.laterwithboot.item.metadata.UrlMetadataRetriever;
import com.example.laterwithboot.user.User;
import com.example.laterwithboot.user.UserMapper;
import com.example.laterwithboot.user.UserService;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final UserService userService;
    private final UrlMetadataRetriever metadataRetriever;

    @Override
    public List<ItemDto> getItems(GetItemRequest req) {
        QItem item = QItem.item;
        List<BooleanExpression> conditions = new ArrayList<>();
        conditions.add(item.user.id.eq(req.getUserId()));

        ItemState state = req.getState();
        if (!state.equals(ItemState.ALL)) {
            conditions.add(makeStateCondition(state));
        }

        ItemContentType contentType = req.getContentType();
        if (!contentType.equals(ItemContentType.ALL)) {
            conditions.add(makeContentTypeCondition(contentType));
        }

        if (req.getTags() != null && !req.getTags().isEmpty()) {
            conditions.add(item.tags.any().in(req.getTags()));
        }

        BooleanExpression finalCondition = conditions.stream()
                .reduce(BooleanExpression::and)
                .get();

        Sort sort = makeOrderByClause(req.getSorting());
        PageRequest request = PageRequest.of(0, req.getLimit(), sort);

        Iterable<Item> items = repository.findAll(finalCondition, request);

        return ItemMapper.toItemDtos(items);
    }

    @Transactional
    @Override
    public ItemDto addNewItem(long userId, ItemInDto itemDto) {
        UrlMetadata data = metadataRetriever.retrieve(itemDto.getUrl());
        User user = UserMapper.toUser(userService.findUserById(userId));
        Optional<Item> maybeExistingItem = repository.findByUserAndResolvedUrl(user, data.getResolvedUrl());
        if (maybeExistingItem.isEmpty()) {
            Item item = ItemMapper.toItemWithMetadata(itemDto, data);
            item.setUser(user);
            return ItemMapper.toItemDto(repository.save(item));
        }
        Item existingItem = maybeExistingItem.get();
        if (itemDto.getTags() != null && !itemDto.getTags().isEmpty()) {
            existingItem.getTags().addAll(itemDto.getTags());
            repository.save(existingItem);
        }
        return ItemMapper.toItemDto(existingItem);
    }

    @Transactional
    @Override
    public void deleteItem(long userId, long itemId) {
        repository.deleteByUserIdAndId(userId, itemId);
    }

    @Override
    public ItemDto findById(long itemId) {
        return ItemMapper.toItemDto(repository.findById(itemId).orElseThrow(ItemNotFoundException::new));
    }

    private BooleanExpression makeStateCondition(ItemState state) {
        if (state.equals(ItemState.READ)) {
            return QItem.item.unread.isFalse();
        } else {
            return QItem.item.unread.isTrue();
        }
    }

    private BooleanExpression makeContentTypeCondition(ItemContentType contentType) {
        if (contentType.equals(ItemContentType.IMAGE)) {
            return QItem.item.mimeType.eq("image");
        } else if (contentType.equals(ItemContentType.VIDEO)) {
            return QItem.item.mimeType.eq("video");
        } else {
            return QItem.item.mimeType.eq("text");
        }

    }

    private Sort makeOrderByClause(ItemSorting sort) {
        switch (sort) {
            case TITLE:
                return Sort.by("title").ascending();
            case OLDEST:
                return Sort.by("dateResolved").ascending();
            default:
                return Sort.by("dateResolved").descending();
        }
    }
}
