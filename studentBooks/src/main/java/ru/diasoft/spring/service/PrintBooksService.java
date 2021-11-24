package ru.diasoft.spring.service;

import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;

public interface PrintBooksService {
    void getAllBooks();
    void getBooksByAuthor(String authorName) throws Exception;
    void printBookInfo(Book book, Author author, Genre genre);
    void printCommentsBook(String bookName);
    void getBooksByGenre(String genreName) throws Exception;
}
