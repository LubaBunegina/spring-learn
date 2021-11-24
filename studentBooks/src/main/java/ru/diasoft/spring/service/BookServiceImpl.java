package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.diasoft.spring.dao.AuthorDao;
import ru.diasoft.spring.dao.GenreDao;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.domain.Genre;
import ru.diasoft.spring.repository.AuthorRepository;
import ru.diasoft.spring.repository.BookRepository;
import ru.diasoft.spring.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookDao;
    private final GenreRepository genreDao;
    private final AuthorRepository authorDao;

    @Override
    public void insert(String bookName, String genreName, String authorName) throws Exception {

        if(genreName == null || bookName == null || authorName == null){
            throw new Exception("genreName or bookName or authorName is empty");
        }

        Optional<Genre> optionalGenre = genreDao.findByName(genreName);
        Optional<Author> optionalAuthor = authorDao.findByName(authorName);

        Genre genre;
        Author author;
        if(optionalGenre.isPresent()){
            genre = optionalGenre.get();
        } else {
            optionalGenre = Optional.of(new Genre());
            genre = optionalGenre.get();
            genre.setName(genreName);
        }
        if(optionalAuthor.isPresent()){
            author = optionalAuthor.get();
        } else {
            optionalAuthor = Optional.of(new Author());
            author = optionalAuthor.get();
            author.setName(authorName);
        }

        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);
        book.setGenre(genre);
        bookDao.saveAndFlush(book);
    }

    @Override
    public void update(Long bookId, String bookName, String authorName, String genreName) throws Exception {
        Book bookUpdate;
        if(bookId == null || bookName == null){
            throw new Exception("bookId or bookName is empty");
        } else {
            Optional<Book> optionalBook = bookDao.findById(bookId);
            if(!optionalBook.isPresent()){
                throw new Exception("Book not found id=" + bookId);
            } else {
                bookUpdate = optionalBook.get();
                Optional<Genre> optionalGenre = genreDao.findByName(genreName);
                Optional<Author> optionalAuthor = authorDao.findByName(authorName);
                Genre genre;
                Author author;
                if(optionalGenre.isPresent()){
                    genre = optionalGenre.get();
                } else {
                    genre = new Genre();
                    genre.setName(genreName);
                }
                if(optionalAuthor.isPresent()){
                    author = optionalAuthor.get();
                } else {
                    author = new Author();
                    author.setName(authorName);
                }
                bookUpdate.setName(bookName);
                bookUpdate.setGenre(genre);
                bookUpdate.setAuthor(author);

                bookDao.save(bookUpdate);
            }
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        if(id == null){
            throw new Exception("BookId is empty");
        } else {
            bookDao.deleteBookById(id);
        }
    }

    @Override
    public Book getById(Long id) {
        Optional<Book> optionalBook = bookDao.findById(id);
        return optionalBook.get();
    }

    @Override
    public List<Book> getBookByAuthor(String authorName) throws Exception {
        Optional<Author> optionalAuthor = authorDao.findByName(authorName);
        if(!optionalAuthor.isPresent()){
            throw new Exception("Author not found with name " + authorName);
        }
        return bookDao.findByAuthor(optionalAuthor.get());
    }

    @Override
    public List<Book> getBookByGenre(String genreName) throws Exception {
        Optional<Genre> optionalGenre = genreDao.findByName(genreName);
        if(!optionalGenre.isPresent()){
            throw new Exception("Genre not found with name " + genreName);
        }
        return bookDao.findByGenre(optionalGenre.get());
    }

    @Override
    public List<Book> getAll() {
        return bookDao.findAll();
    }

}
