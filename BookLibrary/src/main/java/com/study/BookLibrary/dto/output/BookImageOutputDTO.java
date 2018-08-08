package com.study.BookLibrary.dto.output;

public class BookImageOutputDTO {

  private Long id;
  private String fileName;
  private byte[] imageDataFiles;

  public BookImageOutputDTO() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public byte[] getImageDataFiles() {
    return imageDataFiles;
  }

  public void setImageDataFiles(byte[] imageDataFiles) {
    this.imageDataFiles = imageDataFiles;
  }
}