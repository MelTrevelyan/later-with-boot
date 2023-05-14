package com.example.laterwithboot.itemnote;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class ItemNoteController {

    private final ItemNoteService service;

    @GetMapping(params = "url")
    public List<ItemNoteDto> searchByUrl(@RequestHeader("X-Later-User-Id") long userId,
                                         @RequestParam String url) {
        return service.searchNotesByUrl(url, userId);
        // возвращает список пользовательских заметок к ссылкам, соответствующим переданному URL-адресу или его части
    }

    @GetMapping(params = "tag")
    public List<ItemNoteDto> searchByTags(@RequestHeader("X-Later-User-Id") long userId,
                                          @RequestParam String tag) {
        return service.searchNotesByTag(userId, tag);
        // возвращает список заметок пользователя к ссылкам с указанным тегом
    }

    @GetMapping
    public List<ItemNoteDto> listAllNotes(@RequestHeader("X-Later-User-Id") long userId,
                                          @RequestParam(defaultValue = "0") int from,
                                          @RequestParam(defaultValue = "10") int size) {
        return service.listAllItemsWithNotes(userId, from, size);
        // возвращает набор пользовательских заметок, соответствующий указанным параметрам пагинации
    }

    @PostMapping
    public ItemNoteDto add(@RequestHeader("X-Later-User-Id") Long userId, @RequestBody ItemNoteDto itemNote) {
        // добавляет новую заметку к сохранённой ссылке
        return service.addNewItemNote(userId, itemNote);
    }
}
