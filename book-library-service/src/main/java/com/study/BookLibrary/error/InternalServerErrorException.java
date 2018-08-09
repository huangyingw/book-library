package com.study.BookLibrary.error;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends ServiceException {

  public InternalServerErrorException(String message, ServiceErrorCode errorCode) {
    super(message, HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
  }
}
