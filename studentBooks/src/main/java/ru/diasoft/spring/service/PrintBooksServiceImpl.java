package ru.diasoft.spring.service;

import org.springframework.stereotype.Service;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Comment;
import ru.diasoft.spring.domain.Genre;

import java.util.List;

@Service
public class PrintBooksServiceImpl implements PrintBooksService{

    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    public PrintBooksServiceImpl(BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
    }

    @Override
    public void getAllBooks(){
        List<Book> books = bookService.getAll();
        printAllBooks(books);
    }

    @Override
    public void getBooksByAuthor(String authorName) throws Exception {
        Author author = authorService.getByName(authorName);
        if(author != null) {
            List<Book> books = bookService.getBookByAuthor(author.getName());
            if(books != null && books.size() == 0){
                System.out.println("No books by " + authorName);
                return;
            }
            printAllBooks(books);
        } else {
            System.out.println("Author with name " + authorName + " not found");
        }
    }

    @Override
    public void getBooksByGenre(String genreName) throws Exception {
        Genre genre = genreService.getByName(genreName);
        if(genre != null) {
            List<Book> books = bookService.getBookByGenre(genre.getName());
            printAllBooks(books);
        } else {
            System.out.println("Genre with name " + genreName + " not found");
        }
    }

    @Override
    public void printBookInfo(Book book, Author author, Genre genre) {
        System.out.println("ID: " + book.getId()
                + " Name: " + book.getName()
                + " Genre: " + genre.getName()
                + " Author: " + author.getName());
    }

    @Override
    public void printCommentsBook(String bookName) {
        List<Comment> commentList = commentService.getCommentByBook(bookName);
        if(commentList != null && commentList.size() == 0){
            System.out.println("Comments not found for book with name " + bookName);
        }
        for(Comment comment : commentList){
            System.out.println(comment.getAuthor() + ": " + comment.getText());
        }
    }

    private void printAllBooks(List<Book> books){
        for(Book b : books){
            printBookInfo(b, b.getAuthor(), b.getGenre());
        }
    }
}
