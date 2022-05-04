package ru.diasoft.spring.feign;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

import static java.nio.charset.Charset.defaultCharset;
import static org.springframework.util.StreamUtils.copyToString;

public class LibraryMocks {

    public static void setupMockBooksResponse(WireMockServer mockService) throws IOException {
        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/library/books/1"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                LibraryMocks.class.getClassLoader().getResourceAsStream("library_books.json"),
                                                defaultCharset()))));


        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/library/students/1"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                LibraryMocks.class.getClassLoader().getResourceAsStream("library_books.json"),
                                                defaultCharset()))));



        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/library/students/1/books/1"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                LibraryMocks.class.getClassLoader().getResourceAsStream("library_books.json"),
                                                defaultCharset()))));

        mockService.stubFor(WireMock.post(WireMock.urlEqualTo("/api/library"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                LibraryMocks.class.getClassLoader().getResourceAsStream("library_create.json"),
                                                defaultCharset()))));


        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/students?studentName=IvanovIvanPetrovich"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                LibraryMocks.class.getClassLoader().getResourceAsStream("student_books.json"),
                                                defaultCharset()))));

        mockService.stubFor(WireMock.get(WireMock.urlEqualTo("/api/students/1"))
                .willReturn(
                        WireMock.aResponse()
                                .withStatus(HttpStatus.OK.value())
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(
                                        copyToString(
                                                LibraryMocks.class.getClassLoader().getResourceAsStream("student_books.json"),
                                                defaultCharset()))));

    }




}
