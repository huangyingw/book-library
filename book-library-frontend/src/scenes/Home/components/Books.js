import React, { Component } from 'react';
import axios from 'axios';

import './Books.css';

class Books extends Component {
  state = {
    allBooks: [],
  };

  componentWillMount() {
    this.getAllBooks();
  }

  getAllBooks() {
    const url = SERVER_URL + '/library/book';
    axios.get(url)
      .then(response => {
        if (response.status == 200) {
          this.setState({
            allBooks: response.data
          })
        }
      })
  }

  render() {
    return (
      <div>
        <table>
          <tbody>
            {this.state.allBooks.map(map =>
              <tr key={map.id} className='book'>
                <td><img src={"data:image/svg+xml;base64," + map.bookImage.imageDataFiles} width='150' height='200' alt='map' /></td>
                <td id="description">
                  <p><b>Tytuł:</b> {map.title}</p>
                  <p><b>Opis:</b> {map.description}</p>
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default Books;