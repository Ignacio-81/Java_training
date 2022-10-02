package com.ayi.curso.rest.serv.app.exception;

public class WriteAccessException extends ReadAccessException{

    public WriteAccessException(String message) {
        super(message);
    }
}
