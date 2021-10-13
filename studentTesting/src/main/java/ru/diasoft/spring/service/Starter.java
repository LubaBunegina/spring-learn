package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Starter implements CommandLineRunner {

    private final StudentService studentService;
    private final ScannerServiceImp scannerService;

    @Override
    public void run(String... args) throws Exception {
        scannerService.scanStudentName();
        studentService.execute();
    }
}
