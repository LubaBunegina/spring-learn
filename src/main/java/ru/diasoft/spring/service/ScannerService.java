package ru.diasoft.spring.service;

public interface ScannerService {
    String scanStudentName();
    String scanAnswerOnQuestion(String question, String answers);
}
