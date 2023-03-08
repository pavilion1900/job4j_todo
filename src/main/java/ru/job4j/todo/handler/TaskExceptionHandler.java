package ru.job4j.todo.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.todo.exception.ModelNotFoundException;

@ControllerAdvice
public class TaskExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public String handleException(ModelNotFoundException exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "exception/message";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception exception, Model model) {
        model.addAttribute("errorMessage", exception.getMessage());
        return "exception/message";
    }
}
