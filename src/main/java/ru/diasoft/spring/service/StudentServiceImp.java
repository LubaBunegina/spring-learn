package ru.diasoft.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StudentServiceImp implements StudentService {

    private ScannerService scanner;

    private LoaderService loaderService;

    private PrintService printService;

    @Autowired
    public StudentServiceImp(ScannerService scanner, LoaderService loaderService,
                                    PrintService printService) {
        this.scanner = scanner;
        this.loaderService = loaderService;
        this.printService = printService;
    }

    public int execute() throws IOException {
        int score = scanner.getCommonScore();

        if(score >= loaderService.getTestScore()){
            printService.printCommonInfo("Congratulations. you passed the test successfully!");
            return 1;
        } else {
            printService.printCommonInfo("Test failed!");
            return 0;
        }
    }
}
