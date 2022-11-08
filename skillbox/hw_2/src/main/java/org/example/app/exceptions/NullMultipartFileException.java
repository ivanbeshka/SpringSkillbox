package org.example.app.exceptions;

public class NullMultipartFileException extends Exception
{
    private final String message;

    public NullMultipartFileException(String message)
    {
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
