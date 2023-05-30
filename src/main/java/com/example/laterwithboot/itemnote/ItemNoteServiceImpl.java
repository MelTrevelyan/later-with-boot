package com.example.laterwithboot.itemnote;

import com.example.laterwithboot.item.Item;
import com.example.laterwithboot.item.ItemMapper;
import com.example.laterwithboot.item.ItemService;
import com.example.laterwithboot.user.User;
import com.example.laterwithboot.user.UserMapper;
import com.example.laterwithboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemNoteServiceImpl implements ItemNoteService {

    private final ItemNoteRepository repository;
    private final ItemService itemService;
    private final UserService userService;

    @Override
    public ItemNoteDto addNewItemNote(long userId, ItemNoteDto itemNoteDto) {
        User user = UserMapper.toUser(userService.findUserById(userId));
        Item item = ItemMapper.toItem(itemService.findById(itemNoteDto.getItemId()));
        item.setUser(user);
        return ItemNoteMapper.toItemNoteDto(repository.save(ItemNoteMapper.toItemNote(itemNoteDto, item)));
    }

    @Override
    public List<ItemNoteDto> searchNotesByUrl(String url, Long userId) {
        return ItemNoteMapper.toItemNoteDtos(repository.findByItemResolvedUrlContainingAndItemUserId(url, userId));
    }

    @Override
    public List<ItemNoteDto> searchNotesByTag(long userId, String tag) {
        return ItemNoteMapper.toItemNoteDtos(repository.findItemNotesByTagAndUser(tag, userId));
    }

    @Override
    public List<ItemNoteDto> listAllItemsWithNotes(long userId, int from, int size) {
        PageRequest request = PageRequest.of(from > 0 ? from / size : 0, size);
        return repository.findAllByItemUserId(userId, request)
                .map(ItemNoteMapper::toItemNoteDto)
                .getContent();
    }
}
