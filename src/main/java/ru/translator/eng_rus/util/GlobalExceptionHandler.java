package ru.translator.eng_rus.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler ({RuntimeException.class, Exception.class})
    public String handleAllExceptions(Model model){
        int statusCode = HttpStatus.BAD_REQUEST.value();
        model.addAttribute("status", statusCode);
        return "Error";
    }
}
