import React, { Component } from 'react';
import axios from 'axios';

import BookImage from '../BookImage/BookImage';
import './Books.css';

export default class Books extends Component {
  state = {
    allBooks: []
  };

  componentWillMount() {
    this.getAllBooks();
  }

  getAllBooks() {
    const url = SERVER_URL + '/library/book';
    axios.get(url)
      .then((response) => {
        if (response.status === 200) {
          this.setState({
            allBooks: response.data
          });
        }
      });
  }

  render() {
    return (
      <div id="books-container">
        <table>
          <tbody>
            {this.state.allBooks.map(map => (
              <tr key={map.id} className="book">
                <td className="bookImage"><BookImage bookId={map.id} /></td>
                <td id="description">
                  <b>Tytuł:</b>
                  {map.title}
                  <b>Opis:</b>
                  {map.description }
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  }
}