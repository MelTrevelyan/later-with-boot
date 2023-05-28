package com.example.laterwithboot.item;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ItemDto {
    private Long id;
    private Long userId ;
    private String url;
    private Set<String> tags;
}
