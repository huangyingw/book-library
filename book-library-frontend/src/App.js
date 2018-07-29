import React, { Component } from 'react';
import './App.css';
import { BrowserRouter, Route, Switch } from "react-router-dom";

import Home from './components/Home';
import Admin from './components/Admin';
import Navigation from './components/Navigation';

class App extends Component {
  render() {
    return (
      <BrowserRouter>
        <div>
          <Navigation />
          <Switch>
            <Route path="/" component={Home} exact />
            <Route path="/admin" component={Admin} />
          </Switch>
        </div>
      </BrowserRouter>
    );
  }
}

export default App;
