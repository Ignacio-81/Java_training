package com.ayi.curso.rest.serv.app.exception;

import java.sql.SQLException;

public class DataBaseException extends SQLException {
    public DataBaseException(String reason) {
        super(reason);
    }
}
