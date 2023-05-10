package com.example.laterwithboot.item;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    private String url;
    @ElementCollection
    @CollectionTable(name="tags", joinColumns=@JoinColumn(name="item_id"))
    @Column(name="name")
    private Set<String> tags = new HashSet<>();
}
