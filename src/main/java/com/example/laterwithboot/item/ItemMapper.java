package com.example.laterwithboot.item;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .userId(item.getUserId())
                .url(item.getUrl())
                .tags(item.getTags())
                .build();
    }
}
