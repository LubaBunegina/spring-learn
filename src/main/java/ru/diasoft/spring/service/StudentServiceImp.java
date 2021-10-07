package ru.diasoft.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.config.MessageConfig;

import java.io.IOException;
import java.util.Locale;

@Service
public class StudentServiceImp implements StudentService {
    private final MessageConfig props;

    private final ScannerService scanner;

    private final LoaderService loaderService;

    private final PrintService printService;
    
    private final MessageSource messRes;

    @Autowired
    public StudentServiceImp(MessageConfig props, ScannerService scanner, LoaderService loaderService,
                             PrintService printService, @Qualifier("messageResourceSB") MessageSource messRes) {
        this.props = props;
        this.scanner = scanner;
        this.loaderService = loaderService;
        this.printService = printService;
        this.messRes = messRes;
    }

    public int execute() throws IOException {
        int score = scanner.getCommonScore();

        if(score >= loaderService.getTestScore()){
            printService.printCommonInfo(messRes.getMessage("resultSuccess", null, new Locale(props.getLocale())));
            return 1;
        } else {
            printService.printCommonInfo(messRes.getMessage("resultFailed", null, new Locale(props.getLocale())));
            return 0;
        }
    }
}
