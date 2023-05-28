package com.example.laterwithboot.itemnote;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemNoteRepository extends JpaRepository<ItemNote, Long> {

    List<ItemNote> findByItemResolvedUrlContainingAndItemUserId(String url, long userId);

    @Query("select itn from ItemNote as itn " +
            "join itn.item as i" +
            " where ?1 member of i.tags and i.user.id = ?2")
    List<ItemNote> findItemNotesByTagAndUser(String tag, long userId);

    Page<ItemNote> findAllByItemUserId(long userId, Pageable page);
}
