import React from 'react';
import { NavLink } from 'react-router-dom';
import { slide as Menu } from 'react-burger-menu';

import crossIcon from '../assets/images/cross_icon.png';
import hamburgerIcon from '../assets/images/hamburger_icon.png';
import './Navigation.css';

const Navigation = () => (
  <div id="outer-container">
    <Menu
      noOverlay
      pageWrapId="page-wrap"
      outerContainerId="outer-container"
      customCrossIcon={<img src={crossIcon} alt="cross-icon" />}
      customBurgerIcon={<img src={hamburgerIcon} alt="burger-icon" />}
    >
      <div id="bm-menu">
        <nav>
          <li>
            <NavLink activeClassName="active" exact to="/">
              <i className="fas fa-home"> Strona główna</i>
            </NavLink>
          </li>
          <li>
            <NavLink activeClassName="active" exact to="/admin">
              <i className="fas fa-toolbox"> Administrator</i>
            </NavLink>
          </li>
        </nav>
      </div>
    </Menu>
  </div>
);

export default Navigation;