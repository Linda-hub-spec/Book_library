package com.linda.bookibrary.demo.repositories;

import com.linda.bookibrary.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author linda.agbaka
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT u FROM User u WHERE u.email= :email")
    Optional<User> findByEmail(String email);
}
