package com.formacionbdi.springboot.app.productos.exception.config;

public class ErrorBody {

  private Integer status;
  private String message;

  public ErrorBody(Integer status, String message) {
    this.status = status;
    this.message = message;
  }

  public ErrorBody() {

  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
