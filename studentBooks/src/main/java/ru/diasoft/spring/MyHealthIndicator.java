package ru.diasoft.spring;

import lombok.RequiredArgsConstructor;
import org.h2.mvstore.DataUtils;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import ru.diasoft.spring.domain.Author;
import ru.diasoft.spring.domain.Book;
import ru.diasoft.spring.repository.AuthorRepository;
import ru.diasoft.spring.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MyHealthIndicator implements HealthIndicator {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public Health health() {
        List<Map<String, Object>> healthInfoMap = countBooksByAuthor();
        return Health.up().withDetail("Count books by author: ", healthInfoMap).build();


    }

    private List<Map<String, Object>> countBooksByAuthor() {
        List<Map<String, Object>> result = new ArrayList<>();
        List<Author> authorList = authorRepository.findAll();
        if(authorList != null && authorList.size() > 0) {
            for(Author author : authorList) {
                Map<String, Object> resMap = new HashMap<>();
                List<Book> bookList = bookRepository.findByAuthor(author);
                resMap.put("authorName", author.getName());
                resMap.put("bookCount", bookList.size());
                result.add(resMap);
            }
        }

        return result;
    }

}