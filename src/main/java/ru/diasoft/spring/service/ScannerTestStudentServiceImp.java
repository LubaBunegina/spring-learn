package ru.diasoft.spring.service;

import java.util.Scanner;

public class ScannerTestStudentServiceImp implements ScannerService{

    private Scanner in;

    public ScannerTestStudentServiceImp(){
        in = new Scanner(System.in);
    }

    public String scanStudentName() {
        System.out.println("Enter your name: ");
        String name = in.nextLine();
        return name;
    }

    public String scanAnswerOnQuestion(String question, String answers) {
        System.out.println("Question: " + question);
        System.out.println("Answers: " + answers);
        String answer = in.nextLine();
        return answer;
    }
}
