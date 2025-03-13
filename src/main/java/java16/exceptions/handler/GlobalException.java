package java16.exceptions.handler;

import java16.exceptions.ForbidenException;
import java16.exceptions.NotFoundException;
import java16.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    //404
    @ExceptionHandler (value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerNotFoundException(NotFoundException e){
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .massage(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }


    @ExceptionHandler ( value = {UsernameNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handlerUserNotFoundException(NotFoundException e){
        return ExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND)
                .massage(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();

    }

    //403
    @ExceptionHandler(ForbidenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse handleForbidenException(ForbidenException e) {
        return ExceptionResponse.builder()
                .status(HttpStatus.FORBIDDEN)
                .massage(e.getMessage())
                .className(e.getClass().getSimpleName())
                .build();
    }

}
