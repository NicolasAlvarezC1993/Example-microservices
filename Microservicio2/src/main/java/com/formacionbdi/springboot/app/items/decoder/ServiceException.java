package com.formacionbdi.springboot.app.items.decoder;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }
}
