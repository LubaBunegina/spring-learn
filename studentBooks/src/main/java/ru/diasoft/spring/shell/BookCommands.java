package ru.diasoft.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.service.AuthorService;
import ru.diasoft.spring.service.BookService;
import ru.diasoft.spring.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    @ShellMethod(value = "Print all books", key = {"books", "get all"})
    public void getAllBooks(){
        List<Book> books = bookService.getAll();
        for(Book book : books){
            bookService.printBookInfo(book,
                    book.getAuthor(),
                    book.getGenre());
        }
    }

    @ShellMethod(value = "Update book name", key = {"update", "modify"})
    public void updateBook(@ShellOption(help = "book name") String bookName,
                           @ShellOption(help = "book id") Long bookId){

        if(bookId == null || bookName == null){
            System.out.println("bookId or bookName is empty");
            return;
        } else {
            Book book = bookService.getById(bookId);
            if(book == null){
                System.out.println("Book not found id=" + bookId);
            } else {
                Book bookUpdate = new Book(bookId, bookName, book.getGenre(), book.getAuthor());
                bookService.update(bookUpdate);
            }
        }
    }

    @ShellMethod(value = "Insert book", key = {"new", "create"})
    public void insertBook(@ShellOption(help = "book name") String bookName,
                           @ShellOption(help = "genre name") String genreName,
                           @ShellOption(help = "author name") String authorName){

        Genre genre = genreService.getByName(genreName);
        Author author = authorService.getByName(authorName);

        Long genreId;
        if(genre == null){
            genreId = genreService.getMaxId() + 1;
            genreService.insert(new Genre(genreId, genreName));
        } else {
            genreId = genre.getId();
        }

        Long authorId;
        if(author == null){
            authorId = authorService.getMaxId() + 1;
            authorService.insert(new Author(authorId, authorName));
        } else {
            authorId = author.getId();
        }

        bookService.insert(new Book(bookService.getMaxId() + 1, bookName,
                                        new Genre(genreId, genreName),
                                        new Author(authorId, authorName)));

        System.out.println("Book added");

    }

    @ShellMethod(value = "Get book by id", key = {"getById", "get book id"})
    public void getBookById(@ShellOption(help = "book id") Long bookId){
        if(bookId == null || !(bookId instanceof Long)){
            System.out.println("book id is empty or not valid");
        } else {
            Book book = bookService.getById(bookId);
            if(book != null){
                bookService.printBookInfo(book, book.getAuthor(), book.getGenre());
            } else {
                System.out.println("Book not found by id = " + bookId);
            }
        }
    }

    @ShellMethod(value = "Get book by author", key = {"getBookByAuthor", "get book author"})
    public void getBookByAuthor(@ShellOption(help = "author name") String authorName){
        if(authorName == null){
            System.out.println("Author name is empty");
        } else {
            Author author = authorService.getByName(authorName);
            if(author == null){
                System.out.println("Author with name + " + authorName + " not found");
            } else {
                List<Book> books = bookService.getByAuthorId(author.getId());
                for(Book book : books) {
                    bookService.printBookInfo(book, book.getAuthor(), book.getGenre());
                }
            }
        }
    }

    @ShellMethod(value = "Delete book", key = {"delete", "delete book"})
    public void deleteBook(@ShellOption(help = "book id") Long bookId){
        if(bookId == null){
            System.out.println("BookId is empty");
        } else {
            if(bookService.getById(bookId) != null){
                bookService.delete(bookId);
                System.out.println("Book deleted");
            } else {
                System.out.println("Book with id = " + bookId + " not found");
            }
        }
    }

}
