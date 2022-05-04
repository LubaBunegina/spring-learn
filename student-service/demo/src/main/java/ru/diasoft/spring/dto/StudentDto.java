package ru.diasoft.spring.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class StudentDto {

    @NonNull
    private long id;

    @NonNull
    private String name;

}
