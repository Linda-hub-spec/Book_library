package com.linda.bookibrary.demo.entity;

import com.linda.bookibrary.demo.enums.BaseStatus;
import com.linda.bookibrary.demo.enums.UserType;
import lombok.Data;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author linda.agbaka
 */

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private BaseStatus status;

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
