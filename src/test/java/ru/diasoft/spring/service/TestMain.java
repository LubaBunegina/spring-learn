package ru.diasoft.spring.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.diasoft.spring.config.AppConfig;

@Configuration
@ComponentScan(basePackages = "ru.diasoft",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                classes = AppConfig.class))
public class TestMain {
}
