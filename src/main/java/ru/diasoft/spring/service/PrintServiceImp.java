package ru.diasoft.spring.service;

import org.springframework.stereotype.Service;

@Service
public class PrintServiceImp implements PrintService {

    public void printCommonInfo(String info) {
        System.out.println("Common information: " + info);
    }

    @Override
    public void printResult(String valueOf) {
        System.out.println(valueOf);
    }
}
