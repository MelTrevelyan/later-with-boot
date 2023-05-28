package com.example.laterwithboot.itemnote;

import com.example.laterwithboot.item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.example.laterwithboot.user.UserMapper.TIME_FORMATTER;

public class ItemNoteMapper {

    public static ItemNoteDto toItemNoteDto(ItemNote itemNote) {
        String dateOfNote = DateTimeFormatter
                .ofPattern(String.valueOf(TIME_FORMATTER))
                .format(itemNote.getCreationDate());

        return new ItemNoteDto(
                itemNote.getId(),
                itemNote.getItem().getId(),
                itemNote.getNoteText(),
                dateOfNote,
                itemNote.getItem().getResolvedUrl()
        );
    }

    public static List<ItemNoteDto> toItemNoteDtos(Collection<ItemNote> itemNotes) {
        List<ItemNoteDto> dtos = new ArrayList<>();
        for (ItemNote itemNote : itemNotes) {
            dtos.add(toItemNoteDto(itemNote));
        }
        return dtos;
    }

    public static ItemNote toItemNote(ItemNoteDto dto, Item item) {
        LocalDateTime creationDate = LocalDateTime.parse(dto.getDateOfNote(), TIME_FORMATTER);
        return ItemNote.builder()
                .id(dto.getId())
                .noteText(dto.getText())
                .item(item)
                .creationDate(creationDate)
                .build();
    }
}
