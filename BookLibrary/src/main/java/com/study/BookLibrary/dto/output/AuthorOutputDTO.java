package com.study.BookLibrary.dto.output;

public class AuthorOutputDTO {

  private String firstName;
  private String lastName;

  public AuthorOutputDTO() {
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
