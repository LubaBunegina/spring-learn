package ru.diasoft.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.spring.dao.LearnDao;
import ru.diasoft.spring.dao.LearnDaoSimple;

@Configuration
public class DaoConfig {
    @Bean
    public LearnDao testDao(){
        return new LearnDaoSimple();
    }
}
