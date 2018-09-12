import React, { Component } from 'react';
import axios from 'axios';

export default class BookLibraryService {
  constructor() {
    this.url = SERVER_URL + '/library';
  }

  getBookImageByBookId(bookId) {
    const url = this.url + '/book-image/book-id/' + bookId;
    axios.get(url)
      .then(response => response.data.imageDataFiles)
      .catch(error => null);
  }
}