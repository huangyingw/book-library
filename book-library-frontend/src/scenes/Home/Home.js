import React, { Component } from 'react';

import Books from './components/Books/Books';
import './Home.css';

export default class Home extends Component {
  render() {
    return (
      <div id="page-wrap">
        <h2>Wypożyczalnia książek</h2>
        <Books />
      </div>
    );
  }
}