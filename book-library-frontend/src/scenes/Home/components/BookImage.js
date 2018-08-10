import React, { Component } from 'react';
import axios from 'axios';

class BookImage extends Component {
  state = {
    bookImage: [],
  };

  componentDidMount() {
    this.getBookImageByBookId(this.props.bookId)
  }

  getBookImageByBookId = (bookId) => {
    const url = SERVER_URL + '/library/book-image/book-id/' + bookId;
    axios.get(url)
      .then(response => {
        if(response.status == 200) {
          this.setState({
            bookImage: response.data.imageDataFiles
          })
        }
      })
  }

  render() {
    return(
      <div>
        <img src={"data:image/svg+xml;base64," + this.state.bookImage} width='150' height='200' alt='map' />
      </div>
    );
  }
}

export default BookImage;