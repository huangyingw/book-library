import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import Home from './scenes/Home/Home';
import Admin from './scenes/Admin/Admin';
import Navigation from './components/Navigation/Navigation';

const Routes = () => (
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

export default Routes;