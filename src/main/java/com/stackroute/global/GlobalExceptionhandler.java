package com.stackroute.global;

import com.stackroute.exceptions.TrackAlreadyExistException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler
{
    @ExceptionHandler(TrackAlreadyExistException.class)
    public String exception(TrackAlreadyExistException e)
    {

        return e.getMessage();
    }
}
