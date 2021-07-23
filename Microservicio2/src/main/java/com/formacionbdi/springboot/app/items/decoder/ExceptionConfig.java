package com.formacionbdi.springboot.app.items.decoder;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorBody> methodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder();
        fieldErrors.forEach(f -> errorMessage.append(f.getField()).append(" ").append(f.getDefaultMessage()).append(" "));
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(errorMessage.toString());
        errorBody.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorBody> businessException (BusinessException e) {
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(e.getMessage());
        errorBody.setStatus(999);
        return new ResponseEntity<>(errorBody, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorBody> dataAccessException (DataAccessException e) {
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(e.getMessage());
        errorBody.setStatus(998);
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorBody> serviceException (ServiceException e) {
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(e.getMessage());
        errorBody.setStatus(997);
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorBody> exception (Exception e) {
        ErrorBody errorBody = new ErrorBody();
        errorBody.setMessage(e.getMessage());
        errorBody.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
