package com.example.laterwithboot.item;

import com.example.laterwithboot.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .userId(item.getUser().getId())
                .url(item.getUrl())
                .tags(item.getTags())
                .build();
    }

    public static List<ItemDto> toItemDtos(Collection<Item> items) {
        List<ItemDto> dtos = new ArrayList<>();
        for (Item item: items) {
            dtos.add(toItemDto(item));
        }
        return dtos;
    }

    public static Item toItem(ItemDto itemDto, User user) {
        return new Item(itemDto.getId(), user, itemDto.getUrl(), itemDto.getTags());
    }
}
