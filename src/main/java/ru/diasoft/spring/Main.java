package ru.diasoft.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.diasoft.spring.service.*;

import java.io.IOException;

@SpringBootApplication
public class Main {

   public static void main(String[] args) throws IOException {

        ApplicationContext context = SpringApplication.run(Main.class, args);

        StudentService testing = context.getBean(StudentService.class);
        testing.execute();

    }
}
