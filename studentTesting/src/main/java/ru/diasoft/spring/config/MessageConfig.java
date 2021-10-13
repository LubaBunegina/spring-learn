package ru.diasoft.spring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "messages")
@Component
@Data
public class MessageConfig {
    private  String basename;
    private  String encoding;
    private String locale;

}
