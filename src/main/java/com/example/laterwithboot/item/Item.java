package com.example.laterwithboot.item;

import com.example.laterwithboot.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
    private String url;
    @ElementCollection
    @CollectionTable(name="tags", joinColumns=@JoinColumn(name="item_id"))
    @Column(name="name")
    private Set<String> tags;
}
