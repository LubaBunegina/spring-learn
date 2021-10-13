package ru.diasoft.spring.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.diasoft.spring.service.MessageService;
import ru.diasoft.spring.service.ScannerService;
import ru.diasoft.spring.service.StudentService;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class StudentTestCommand {

    private final ScannerService scanner;
    private final MessageService messageService;
    private final StudentService service;

    private String studentName;

     @ShellMethod(value = "Ask student name", key = {"ask", "askName"})
    public void askName() {
        studentName = scanner.scanStudentName();
    }

    @ShellMethod(value = "Start student testing", key = {"test", "start"})
    @ShellMethodAvailability(value = "isStudentNameAvailable")
    public void start() throws IOException {
        service.execute();
    }

   private Availability isStudentNameAvailable() {
        return studentName == null ? Availability.unavailable(messageService.getMessage("noTestAvailable")) :
                Availability.available();
    }
}
