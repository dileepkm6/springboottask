package com.stackroute.exceptionhandling;

public class TrackAlreadyExistException extends RuntimeException
{
    public TrackAlreadyExistException()
    {
        super("track already exist");
    }

    public TrackAlreadyExistException(String message) {
        super(message);
    }
}
