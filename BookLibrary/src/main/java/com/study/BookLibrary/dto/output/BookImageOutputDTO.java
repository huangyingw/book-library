package com.study.BookLibrary.dto.output;

public class BookImageOutputDTO {
  private String fileName;
  private byte[] imageDataFiles;

  public BookImageOutputDTO() {
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