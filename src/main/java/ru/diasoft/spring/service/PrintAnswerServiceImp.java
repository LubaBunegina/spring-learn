package ru.diasoft.spring.service;

public class PrintAnswerServiceImp implements PrintService {

    public void printCommonInfo(String info) {
        System.out.println("Common information: " + info);
    }

    public void printResult(String res) {
        System.out.println("Result: " + res);
    }
}
