package ru.diasoft.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestLearn {
    int number;
    String question;
    String answers;
    String rightAnswer;
}
