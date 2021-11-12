package ru.diasoft.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.spring.service.*;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;
    private final CommentService commentService;
    private final PrintBooksService printService;

    @ShellMethod(value = "Print all books", key = {"books", "get all"})
    public void getAllBooks(){
        printService.getAllBooks();
    }

    @ShellMethod(value = "Update book name", key = {"update", "modify"})
    public void updateBook(@ShellOption(help = "book name") String bookName,
                           @ShellOption(help = "author name") String authorName,
                           @ShellOption(help = "genre name") String genreName,
                           @ShellOption(help = "book id") Long bookId) throws Exception {

        bookService.update(bookId, bookName, authorName, genreName);
        System.out.println("Book updated");
    }

    @ShellMethod(value = "Insert book", key = {"new", "create"})
    public void insertBook(@ShellOption(help = "book name") String bookName,
                           @ShellOption(help = "genre name") String genreName,
                           @ShellOption(help = "author name") String authorName) throws Exception {

        bookService.insert(bookName, genreName, authorName);
        System.out.println("Book added");

    }

    @ShellMethod(value = "Get book by author", key = {"getBookByAuthor", "get book author"})
    public void getBookByAuthor(@ShellOption(help = "author name") String authorName) throws Exception {

        printService.getBooksByAuthor(authorName);
    }

    @ShellMethod(value = "Get book by genre", key = {"getBookByGenre", "get book genre"})
    public void getBookByGenre(@ShellOption(help = "genre name") String genreName) throws Exception {

        printService.getBooksByGenre(genreName);
    }

    @ShellMethod(value = "Delete book", key = {"delete", "delete book"})
    public void deleteBook(@ShellOption(help = "book id") Long bookId) throws Exception {

        bookService.delete(bookId);
    }

    @ShellMethod(value = "addComment", key = {"addComment"})
    public void addComment(@ShellOption(help = "book name") String bookName,
                           @ShellOption(help = "author comment") String author,
                           @ShellOption(help = "comment text") String commentText){

        commentService.insert(author, bookName, commentText);

    }

    @ShellMethod(value = "getAllCommentByBook", key = {"getAllComment"})
    public void getAllCommentByBook(@ShellOption(help = "book name") String bookName){

        printService.printCommentsBook(bookName);
    }

}
