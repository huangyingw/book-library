import React, { Component } from 'react';
import axios from 'axios';

import placeholder from '../../../../assets/images/placeholder.png';

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
        if (response.status == 200) {
          this.setState({
            bookImage: "data:image/png+jpg+jpeg;base64," + response.data.imageDataFiles
          })
        }
      })
      .catch(error => {
        this.setState({
          bookImage: placeholder
        })
      })
  }

  render() {
    return (
      <div>
        <img src={this.state.bookImage} width='200' height='200' alt='' />
      </div>
    );
  }
}

export default BookImage;