package com.example.laterwithboot.itemnote;

import com.example.laterwithboot.item.Item;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item_notes")
public class ItemNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "note_text")
    private String noteText;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
    @Column(name = "creation_date")
    private Date creationDate;
}
