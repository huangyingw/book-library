package com.study.BookLibrary.dto.input;

public class AuthorInputDTO {

  private String firstName;
  private String lastName;

  public AuthorInputDTO() {
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
