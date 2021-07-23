package com.formacionbdi.springboot.app.items.decoder;

public class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(String s) {
        super(s);
    }

}
