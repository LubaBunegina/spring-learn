package ru.diasoft.spring.rest;

import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentController {

    private final CommentService service;

    public CommentController(CommentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/comments", method = RequestMethod.GET)
    public List<CommentDto> getAllCommentByBook(@RequestParam("bookName") String bookName) {
        return service.getCommentByBook(bookName).stream()
                .map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/api/comments")
    public CommentDto createNewComment(@RequestBody CommentDto dto) throws Exception {
        Comment savedComment = service.insert(dto.getAuthor(), dto.getBookId(), dto.getText());
        return CommentDto.toDto(savedComment);
    }


}
