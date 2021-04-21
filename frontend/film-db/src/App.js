import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import {BrowserRouter as Router, Switch, Route} from
'react-router-dom' 
//import Home from './pages';
import About from './pages/about';
import Form from './pages/Form';
import Admin from './pages/admin';
import Home from './pages/home';
import AddFilm from './pages/addFilm';

function App() {
  return (
    <Router>
      <Navbar />
      <Switch>
        <Route path='/about' exact component={About} />
        <Route path='/addFilm' exact component={AddFilm} />
        <Route path='/form' exact component={Form} />
        <Route path='/admin' exact component={Admin} />
        <Route path='/home' exact component={Home} />
      </Switch>
    </Router>
  );
}

export default App;
