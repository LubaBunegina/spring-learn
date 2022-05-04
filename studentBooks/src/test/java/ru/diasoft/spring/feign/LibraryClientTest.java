package ru.diasoft.spring.feign;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.diasoft.spring.dto.LibraryDto;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import static ru.diasoft.spring.feign.LibraryMocks.setupMockBooksResponse;

@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { WireMockConfig.class })
@DisplayName("Клиент для работы с библиотекой должен уметь")
public class LibraryClientTest {

    @Autowired
    private WireMockServer mockBooksService;

    @Autowired
    LibraryClient libraryClient;

    @Autowired
    StudentClient studentClient;

    @BeforeEach
    void setUp() throws IOException {
        setupMockBooksResponse(mockBooksService);
    }

    @DisplayName("возвращать связи с bookId")
    @Test
    public void  whenGetLibraryByBookId_ThatLibrariesShouldBeReturn() {
        assertFalse(libraryClient.getLibraryByBookId(1).isEmpty());
    }

    @DisplayName("возвращать связи с studentId")
    @Test
    public void  whenGetLibraryByStudentId_ThatLibrariesShouldBeReturn() {
        assertFalse(libraryClient.getLibraryByStudentId(1).isEmpty());
    }

    @DisplayName("возвращать связи с studentId и bookId")
    @Test
    public void  whenGetLibraryByStudentIdAndBookId_ThatLibrariesShouldBeReturn() {
        assertFalse(libraryClient.getLibraryByStudentIdAndBookId(1,1).isEmpty());
    }

    @DisplayName("создавать связи с studentId и bookId")
    @Test
    public void  whenCreateLibraryByStudentIdAndBookId_ThatLibrariesShouldBeReturn() {
        LibraryDto dto = new LibraryDto();
        dto.setBookId(1);
        dto.setStudentId(1);
        assertNotNull(libraryClient.create(dto).getId());
    }

    @DisplayName("искать студента по имени")
    @Test
    public void whenFindStudentByName_ThatStudentShouldBeReturn() {
        assertNotNull(studentClient.getStudentByName("IvanovIvanPetrovich"));
    }

    @DisplayName("искать студента по id")
    @Test
    public void whenFindStudentById_ThatStudentShouldBeReturn() {
        assertNotNull(studentClient.getStudentById(1));
    }
}
