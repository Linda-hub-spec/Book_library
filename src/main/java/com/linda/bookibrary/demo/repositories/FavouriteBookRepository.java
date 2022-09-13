package com.linda.bookibrary.demo.repositories;

import com.linda.bookibrary.demo.entity.FavouriteBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author linda.agbaka
 */
@Repository
public interface FavouriteBookRepository extends JpaRepository<FavouriteBook,Long> {
}
