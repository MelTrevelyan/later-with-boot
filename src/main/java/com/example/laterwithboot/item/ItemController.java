package com.example.laterwithboot.item;

import com.example.laterwithboot.item.enums.ItemContentType;
import com.example.laterwithboot.item.enums.ItemSorting;
import com.example.laterwithboot.item.enums.ItemState;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping
    public List<ItemDto> get(@RequestHeader("X-Later-User-Id") long userId,
                             @RequestParam(defaultValue = "unread") String state,
                             @RequestParam(defaultValue = "all") String contentType,
                             @RequestParam(defaultValue = "newest") String sort,
                             @RequestParam(defaultValue = "10") int limit,
                             @RequestParam(required = false) List<String> tags) {
        GetItemRequest itemRequest = new GetItemRequest();
        ItemState itemState = ItemState.valueOf(state);
        ItemContentType itemContentType = ItemContentType.valueOf(contentType);
        ItemSorting itemSorting = ItemSorting.valueOf(sort);

        itemRequest.setUserId(userId);
        itemRequest.setState(itemState);
        itemRequest.setContentType(itemContentType);
        itemRequest.setSorting(itemSorting);
        itemRequest.setLimit(limit);
        itemRequest.setTags(tags);

        return itemService.getItems(itemRequest);
    }

    @PostMapping
    public ItemDto add(@RequestHeader("X-Later-User-Id") Long userId,
                    @RequestBody ItemInDto itemDto) {
        return itemService.addNewItem(userId, itemDto);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@RequestHeader("X-Later-User-Id") long userId,
                           @PathVariable long itemId) {
        itemService.deleteItem(userId, itemId);
    }
}
