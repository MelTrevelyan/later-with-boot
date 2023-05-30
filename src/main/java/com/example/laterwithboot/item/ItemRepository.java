package com.example.laterwithboot.item;

import com.example.laterwithboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByUserId(long userId);

    void deleteByUserIdAndId(long userId, long id);

    Optional<Item> findByUserAndResolvedUrl(User user, String resolvedUrl);
}
