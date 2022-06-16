package ru.diasoft.spring.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCommentDto {
    @NonNull
    private long bookId;

    @NonNull
    private String studentName;

    @NonNull
    private String bookComment;
}