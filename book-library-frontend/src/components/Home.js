import React, { Component } from 'react';

import './Home.css';
import Repository from './Repository';

class Home extends Component {
	constructor(props) {
		super(props);
		this.repository = React.createRef();
	}


	render() {
		return (
			<div>
				<Repository ref={this.repository} />
			</div>
		);
	}
}

export default Home;