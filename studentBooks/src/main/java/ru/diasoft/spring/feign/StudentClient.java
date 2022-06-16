package ru.diasoft.spring.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.diasoft.spring.config.FeignConfig;
import ru.diasoft.spring.dto.StudentDto;

@FeignClient( value = "students",
              url  = "${library.service.url}",
              configuration = FeignConfig.class
            )
public interface StudentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/students")
    StudentDto getStudentByName(@RequestParam String studentName);

    @RequestMapping(method = RequestMethod.GET, value = "/api/students/{id}")
    StudentDto getStudentById(@PathVariable long id);
}
