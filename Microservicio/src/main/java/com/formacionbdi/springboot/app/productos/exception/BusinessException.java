package com.formacionbdi.springboot.app.productos.exception;

public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String s) {
        super(s);
    }

}
