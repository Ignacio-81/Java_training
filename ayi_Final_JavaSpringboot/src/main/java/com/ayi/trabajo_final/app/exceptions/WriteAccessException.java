package com.ayi.trabajo_final.app.exceptions;

public class WriteAccessException extends ReadAccessException{

    public WriteAccessException(String message) {
        super(message);
    }
}
