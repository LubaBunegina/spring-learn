package ru.diasoft.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.spring.dao.LearnDao;
import ru.diasoft.spring.service.TestService;
import ru.diasoft.spring.service.TestServiceImp;

@Configuration
public class ServicesConfig {
    @Bean
    public TestService testService(LearnDao dao){
        return new TestServiceImp(dao);
    }

}
