import React from 'react';
import { NavLink } from 'react-router-dom';

const Navigation = () => {
	return (
		<nav>
			<ul>
				<li><NavLink activeClassName='current' to='/'>Home</NavLink></li>
				<li><NavLink activeClassName='current' to='/admin'>Admin</NavLink></li>
			</ul>
		</nav>
	);
};

export default Navigation;