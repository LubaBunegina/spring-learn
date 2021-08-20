package ru.diasoft.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.spring.dao.TestDao;
import ru.diasoft.spring.service.TestService;
import ru.diasoft.spring.service.TestServiceImp;

@Configuration
public class ServicesConfig {
    @Bean
    public TestService testService(TestDao dao){
        return new TestServiceImp(dao);
    }

}
