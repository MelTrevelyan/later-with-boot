package com.example.laterwithboot.item;

import java.util.List;

public interface ItemService {
    ItemDto addNewItem(long userId, ItemDto itemDto);

    List<ItemDto> getItems(long userId);

    void deleteItem(long userId, long itemId);

    ItemDto findById(long itemId);
}
