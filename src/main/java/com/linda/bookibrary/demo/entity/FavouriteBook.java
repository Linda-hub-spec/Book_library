package com.linda.bookibrary.demo.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author linda.agbaka
 */

@Data
@Entity
public class FavouriteBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;
}
