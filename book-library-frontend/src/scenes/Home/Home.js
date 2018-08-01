import React, { Component } from 'react';

import Books from './components/Books'
import './Home.css';

class Home extends Component {
	constructor(props) {
		super(props);
		this.books = React.createRef();
	}

	render() {
		return (
			<div>
				<h2>Wypożyczalnia książek</h2>
				<Books ref={this.books} />
			</div>
		);
	}
}

export default Home;