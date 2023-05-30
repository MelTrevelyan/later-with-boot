package com.example.laterwithboot.item;

import java.util.List;

public interface ItemService {
    ItemDto addNewItem(long userId, ItemInDto itemDto);

    List<ItemDto> getItems(GetItemRequest req);

    void deleteItem(long userId, long itemId);

    ItemDto findById(long itemId);
}
