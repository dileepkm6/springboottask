package com.stackroute.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(TrackAlreadyExistException.class)
    public String handleTrackAlreadyExistException(TrackAlreadyExistException e)
    {

        return e.getMessage();
    }

}
