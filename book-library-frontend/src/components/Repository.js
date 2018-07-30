import React, {Component} from 'react';
import axios from 'axios';

class Repository extends Component {
  state = {
    allBooks: []
  }

  componentWillMount() {
    this.getAllBooks();
  }

  getAllBooks() {
    const url = SERVER_URL + '/library/book';
    axios.get(url)
      .then(response => {
        this.setState({
          allBooks: response.data
        })
      })
    console.log("All books: ", this.state.allBooks)
  }

  render () {
    return (
      <div>
        <table>
          <tbody>
            {this.state.allBooks.map(map =>
                <tr key={map.id}>
                  <td><img src={"data:image/svg+xml;base64," + map.bookImage.imageDataFiles} width='150' height='200' alt='map' /></td>
                  <td id="description">
                    Name: {map.title}<p />
                    Description: {map.description}
                  </td>
                </tr>
            )}
          </tbody>
        </table>
      </div>
    );
  }
}

export default Repository;