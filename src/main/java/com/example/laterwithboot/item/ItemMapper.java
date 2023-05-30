package com.example.laterwithboot.item;

import com.example.laterwithboot.item.metadata.UrlMetadata;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static com.example.laterwithboot.util.Constants.TIME_FORMATTER;

public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        ZoneId timeZone = ZoneId.systemDefault();

        return ItemDto.builder()
                .id(item.getId())
                .normalUrl(item.getNormalUrl())
                .resolvedUrl(item.getResolvedUrl())
                .mimeType(item.getMimeType())
                .title(item.getTitle())
                .hasImage(item.isHasImage())
                .hasVideo(item.isHasVideo())
                .unread(item.isUnread())
                .dateResolved(LocalDateTime.ofInstant(item.getDateResolved(), timeZone).format(TIME_FORMATTER))
                .tags(item.getTags())
                .build();
    }

    public static List<ItemDto> toItemDtos(Iterable<Item> items) {
        List<ItemDto> dtos = new ArrayList<>();
        for (Item item: items) {
            dtos.add(toItemDto(item));
        }
        return dtos;
    }

    public static Item toItem(ItemDto itemDto) {
        Item item = new Item();
        ZoneOffset zoneOffset = ZoneOffset.UTC;
        Instant dateResolved = LocalDateTime.parse(itemDto.getDateResolved(), TIME_FORMATTER).toInstant(zoneOffset);

        item.setId(itemDto.getId());
        item.setNormalUrl(itemDto.getNormalUrl());
        item.setResolvedUrl(itemDto.getResolvedUrl());
        item.setMimeType(itemDto.getMimeType());
        item.setTitle(itemDto.getTitle());
        item.setHasVideo(itemDto.isHasVideo());
        item.setHasImage(itemDto.isHasImage());
        item.setUnread(itemDto.isUnread());
        item.setDateResolved(dateResolved);
        item.setTags(itemDto.getTags());
        return item;
    }

    public static Item toItemWithMetadata(ItemInDto itemDto, UrlMetadata data) {
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
