package com.example.laterwithboot.item;

import com.example.laterwithboot.exception.ItemNotFoundException;
import com.example.laterwithboot.item.metadata.UrlMetadata;
import com.example.laterwithboot.item.metadata.UrlMetadataRetriever;
import com.example.laterwithboot.user.User;
import com.example.laterwithboot.user.UserMapper;
import com.example.laterwithboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ItemDto> getItems(long userId) {
        return ItemMapper.toItemDtos(repository.findByUserId(userId));
    }

    @Transactional
    @Override
    public ItemDto addNewItem(long userId, ItemDto itemDto) {
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
}
