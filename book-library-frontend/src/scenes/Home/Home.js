import React, { Component } from 'react';

import Books from './components/Books/Books';
import './Home.css';

class Home extends Component {
  constructor(props) {
    super(props);
  }

  render() {
    return (
      <div>
        <h2>Wypożyczalnia książek</h2>
        <Books />
      </div>
    );
  }
}

export default Home;