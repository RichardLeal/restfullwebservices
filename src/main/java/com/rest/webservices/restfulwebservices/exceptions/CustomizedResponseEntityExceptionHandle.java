package com.rest.webservices.restfulwebservices.exceptions;

import com.rest.webservices.restfulwebservices.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandle
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handAllException(Exception exception, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), exception.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handUserNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), userNotFoundException.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException,
                                                                  HttpHeaders httpHeaders, HttpStatus httpStatus, WebRequest webRequest) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(new Date(), "Validation Failed", methodArgumentNotValidException.getBindingResult().toString());

        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
