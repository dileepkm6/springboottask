package com.stackroute.exceptions;

public class TrackAlreadyExistException extends Exception
{
    public TrackAlreadyExistException() {
    }

    public TrackAlreadyExistException(String message) {
        super("Track already exist in database");
    }
}
