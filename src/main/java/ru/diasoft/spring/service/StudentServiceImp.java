package ru.diasoft.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.config.MessageConfig;

import java.io.IOException;

@Service
public class StudentServiceImp implements StudentService {

    private final ScannerService scanner;

    private final LoaderService loaderService;

    private final PrintService printService;

    private final MessageService messageService;


    @Autowired
    public StudentServiceImp(ScannerService scanner, LoaderService loaderService,
                             PrintService printService, MessageService messageService) {
        this.scanner = scanner;
        this.loaderService = loaderService;
        this.printService = printService;
        this.messageService = messageService;
    }

    public int execute() throws IOException {
        int score = scanner.getCommonScore();

        if(score >= loaderService.getTestScore()){
            printService.printCommonInfo(messageService.getMessage("resultSuccess"));
            return 1;
        } else {
            printService.printCommonInfo(messageService.getMessage("resultFailed"));
            return 0;
        }
    }
}
