package com.stackroute.exceptionhandling;

public class TrackNotFoundException extends RuntimeException
{
    public TrackNotFoundException()
    {
        super("no track found here");
    }

    public TrackNotFoundException(String message) {
        super(message);
    }
}
