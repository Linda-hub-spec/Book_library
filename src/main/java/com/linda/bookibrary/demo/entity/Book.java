package com.linda.bookibrary.demo.entity;

import com.linda.bookibrary.demo.enums.BaseStatus;
import lombok.Data;


import javax.persistence.*;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.Date;

/**
 * @author linda.agbaka
 */

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    private String author;

    private LocalDate publishedDate;


    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BaseStatus status;

    @ManyToOne
    private BookCategory bookCategory;

    private Date createdDate;

    private String modifiedBy;

    private Date modifiedDate;

    @PrePersist
    private void setCreatedDate() {
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.status = BaseStatus.ACTIVE;

    }

    @PreUpdate
    private void updatedAt() {
        this.modifiedDate = new Timestamp(System.currentTimeMillis());
    }
}
