package ru.diasoft.spring.service;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.diasoft.spring.config.MessageConfig;
import java.util.Locale;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageSource messageSource;
    private final MessageConfig config;

    public String getMessage(String bundle){
        return messageSource.getMessage(bundle, null, new Locale(config.getLocale()));
    }
}
