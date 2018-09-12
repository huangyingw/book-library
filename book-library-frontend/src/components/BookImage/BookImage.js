import React, { Component } from 'react';
import BookLibraryService from '../../api/BookLibraryService';

export default class BookImage extends Component {
  state = {
    bookImage: []
  };

  componentDidMount() {
    this.getBookImageByBookId(this.props.bookId);
  }

  getBookImageByBookId = (bookId) => {
    const API = new BookLibraryService();
    this.setState({
      bookImage: 'data:image/png+jpg+jpeg;base64' + API.getBookImageByBookId(bookId)
    });
  }

  render() {
    return (
      <div>
        <img src={this.state.bookImage} width="200" height="200" alt="" />
      </div>
    );
  }
}