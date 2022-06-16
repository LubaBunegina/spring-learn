package ru.diasoft.spring.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class StudentDto {

    private long id;

    private String name;

}
