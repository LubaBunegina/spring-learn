package ru.diasoft.spring.security;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    @Size(min=5, message = "Не меньше 5 знаков")
    private String password;

    @Column(name = "enabled")
    private int enabled;


}
