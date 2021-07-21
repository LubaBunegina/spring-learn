package ru.diasoft.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestLearn {
    String question;
    String answers;

    public void printTest(){
        System.out.println("Question: " + this.getQuestion());
        System.out.println("Answers: " + this.getAnswers());
        System.out.println();
    }
}
