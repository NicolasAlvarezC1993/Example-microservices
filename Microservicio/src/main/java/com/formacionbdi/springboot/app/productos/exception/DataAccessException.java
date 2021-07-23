package com.formacionbdi.springboot.app.productos.exception;

public class DataAccessException extends RuntimeException {

    public DataAccessException() {
    }

    public DataAccessException(String message) {
        super(message);
    }
}
