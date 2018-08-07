package com.study.BookLibrary.error;

import org.springframework.http.HttpStatus;

public class ServiceException extends RuntimeException {

  private HttpStatus status;

  public ServiceException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return String.format("Exception message: {%s}, status code: {%s}", super.getMessage(),
        status.toString());
  }
}
