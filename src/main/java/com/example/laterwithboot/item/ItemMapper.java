package com.example.laterwithboot.item;

import com.example.laterwithboot.item.metadata.UrlMetadata;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .userId(item.getUser().getId())
                .url(item.getResolvedUrl())
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

    public static Item toItem(ItemDto itemDto) {
        Item item = new Item();
        item.setResolvedUrl(itemDto.getUrl());
        item.setTags(itemDto.getTags());
        return item;
    }

    public static Item toItemWithMetadata(ItemDto itemDto, UrlMetadata data) {
        Item item = new Item();

        item.setTags(itemDto.getTags());
        item.setNormalUrl(data.getNormalUrl());
        item.setResolvedUrl(data.getResolvedUrl());
        item.setTitle(data.getTitle());
        item.setMimeType(data.getMimeType());
        item.setHasImage(data.isHasImage());
        item.setHasVideo(data.isHasVideo());
        item.setDateResolved(data.getDateResolved());

        return item;
    }
}
