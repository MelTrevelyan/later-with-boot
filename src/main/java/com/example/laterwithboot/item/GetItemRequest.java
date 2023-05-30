package com.example.laterwithboot.item;

import com.example.laterwithboot.item.enums.ItemContentType;
import com.example.laterwithboot.item.enums.ItemSorting;
import com.example.laterwithboot.item.enums.ItemState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GetItemRequest {

    private Long userId;
    private ItemState state;
    private ItemSorting sorting;
    private ItemContentType contentType;
    private int limit;
    private List<String> tags;
}
