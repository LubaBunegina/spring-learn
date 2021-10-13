package ru.diasoft.spring.service;

import java.io.IOException;

public interface ScannerService {
    String scanStudentName();
    String scanAnswerOnQuestion(String question, String answers);
    int getCommonScore() throws IOException;
}
