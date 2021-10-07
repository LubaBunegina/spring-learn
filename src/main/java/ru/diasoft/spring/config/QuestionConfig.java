package ru.diasoft.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "questions")
@Component
@Data
public class QuestionConfig {
    private  String dir;
    private  Integer testScore;
}
