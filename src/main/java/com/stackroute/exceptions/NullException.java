package com.stackroute.exceptions;

public class NullException extends Exception
{
    public NullException() {
    }

    public NullException(String message) {
        super("no track exist in database");
    }
}
