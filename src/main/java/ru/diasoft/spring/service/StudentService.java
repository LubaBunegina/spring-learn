package ru.diasoft.spring.service;

import java.io.IOException;

public interface TestingStudentService {
    int execute() throws IOException;
    int getCommonScore() throws IOException;
}
