import React, {Component} from 'react';
import axios from 'axios';

class Repository extends Component {
  state = {
    allBooks: []
  }

  componentWillMount() {
    this.getAll();
  }

  getAll() {
    const url = SERVER_URL + '/library/book';
    axios.get(url)
      .then(response => {
        this.setState({
          allBooks: response.data
        })
      })
    console.log(this.state.allBooks)
  }

  render () {
    return (
      <div>
        <table>
          <tbody>
            {
              this.state.allBooks.map(map =>
                <tr key={map.id}>
                  <td id="description">
                    Name: {map.title}<p />
                    Description: {map.description}
                  </td>
                </tr>
              )
            }
          </tbody>
        </table>
      </div>
    );
  }
}

export default Repository;