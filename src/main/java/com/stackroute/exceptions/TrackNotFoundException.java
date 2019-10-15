package com.stackroute.exceptions;

public class TrackNotFoundException extends Exception
{
    public TrackNotFoundException() {
    }

    public TrackNotFoundException(String message) {
        super(message);
    }
}
