package com.study.BookLibrary.error;

import org.springframework.http.HttpStatus;

public class ConflictException extends ServiceException {

  public ConflictException(String message, ServiceErrorCode errorCode) {
    super(message, HttpStatus.CONFLICT, errorCode);
  }
}
