package com.example.laterwithboot.item;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class ItemInDto {

    private String url;
    private Set<String> tags = new HashSet<>();
}
