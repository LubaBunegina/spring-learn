package ru.diasoft.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.spring.dao.TestDao;
import ru.diasoft.spring.dao.TestDaoSimple;

@Configuration
public class DaoConfig {
    @Bean
    public TestDao testDao(){
        return new TestDaoSimple();
    }
}
