package ru.diasoft.spring.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Data
@Entity
@Table(name = "library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "bookid", nullable = false)
    private long bookId;

    @Column(name = "studentid", nullable = false)
    private long studentId;
}