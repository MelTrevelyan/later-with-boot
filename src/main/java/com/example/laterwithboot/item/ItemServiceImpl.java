package com.example.laterwithboot.item;

import com.example.laterwithboot.exception.ItemNotFoundException;
import com.example.laterwithboot.user.User;
import com.example.laterwithboot.user.UserMapper;
import com.example.laterwithboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository repository;
    private final UserService userService;

    @Override
    public List<ItemDto> getItems(long userId) {
        return ItemMapper.toItemDtos(repository.findByUserId(userId));
    }

    @Transactional
    @Override
    public ItemDto addNewItem(long userId, ItemDto itemDto) {
        User user = UserMapper.toUser(userService.findUserById(userId));
        Item item = ItemMapper.toItem(itemDto, user);
        return ItemMapper.toItemDto(repository.save(item));
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
