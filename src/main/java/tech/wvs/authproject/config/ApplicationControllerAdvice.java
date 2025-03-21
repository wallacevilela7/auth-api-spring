package tech.wvs.authproject.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tech.wvs.authproject.exception.UsernameOrPasswordException;

@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotFoundException(UsernameOrPasswordException exception) {
        return exception.getMessage();
    }
}
