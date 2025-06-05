package com.whs.dev2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.whs.dev2.entity.common.BaseEntity;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;
}
