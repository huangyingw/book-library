import React from 'react';
import { NavLink } from 'react-router-dom';

import './Navigation.css';

const Navigation = () => {
  return (
    <nav>
      <ul>
        <li><NavLink activeClassName='active' exact to='/'><i class="fas fa-home"> Strona główna</i></NavLink></li>
        <li><NavLink activeClassName='active' exact to='/admin'><i class="fas fa-toolbox"> Administrator</i></NavLink></li>
      </ul>
    </nav>
  );
};

export default Navigation;