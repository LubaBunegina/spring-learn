package ru.diasoft.spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageSourceConfig {

    @Bean(name = "messageResourceSB")
    public MessageSource messageSource(MessageConfig props) {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename(props.getBasename());
        messageSource.setDefaultEncoding(props.getEncoding());

        return messageSource;
    }

}
