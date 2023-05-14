package com.example.laterwithboot.itemnote;

import com.example.laterwithboot.item.Item;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@Table(name = "item_notes")
public class ItemNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note_text")
    private String noteText;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    @ToString.Exclude
    private Item item;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
}
