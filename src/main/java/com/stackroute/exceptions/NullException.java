package com.stackroute.exceptions;

public class NullException extends Exception
{

    public NullException() {
        super("no track exist in database");
    }
}
