package com.formacionbdi.springboot.app.items.decoder;

public class DataAccessException extends RuntimeException {

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }
}
