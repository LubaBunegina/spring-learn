package ru.diasoft.spring.rest;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.diasoft.spring.domain.Comment;

@RequiredArgsConstructor
@Data
public class CommentDto {

    private long id;


    private String text;


    private String author;

    private long bookId;

    public CommentDto(long id, String text, String author, long bookId) {
        this.id = id;
        this.author = author;
        this.text = text;
        this.bookId = bookId;
    }


    public static CommentDto toDto(Comment comment) {
        if(comment != null) {
            return new CommentDto(comment.getId(), comment.getText(), comment.getAuthor(), comment.getBook().getId());
        } else {
            return null;
        }
    }
}
