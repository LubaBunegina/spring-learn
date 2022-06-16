package ru.diasoft.spring.dto;

import lombok.NonNull;

public class StudentCommentDto {
    @NonNull
    public long bookId;

    @NonNull
    public String studentName;

    @NonNull
    public String bookComment;
}
