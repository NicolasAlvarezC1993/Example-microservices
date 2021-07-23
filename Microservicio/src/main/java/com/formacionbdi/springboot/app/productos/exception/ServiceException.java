package com.formacionbdi.springboot.app.productos.exception;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
