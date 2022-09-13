package com.linda.bookibrary.demo.repositories;

import com.linda.bookibrary.demo.entity.BookCategory;
import com.linda.bookibrary.demo.enums.BaseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author linda.agbaka
 */
@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory,Long> {

    @Query("SELECT b FROM BookCategory b WHERE b.name = :name")
    Optional<BookCategory> findByName(String name);

    @Query("SELECT b FROM BookCategory b WHERE b.status = :status OR (:status IS NULL)")
    Page<BookCategory> getBookCategory(BaseStatus status, Pageable paging);
}
