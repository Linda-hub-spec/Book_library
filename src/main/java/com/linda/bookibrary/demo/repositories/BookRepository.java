package com.linda.bookibrary.demo.repositories;

import com.linda.bookibrary.demo.entity.Book;
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
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT b FROM Book b WHERE b.id = :bookId")
    Optional<Book> findById(Long bookId);

    @Query("SELECT b FROM Book b WHERE b.status = :status OR (:status IS NULL)")
    Page<Book> getBooks(BaseStatus status, Pageable paging);
}
