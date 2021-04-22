import React from 'react';
import './App.css';
import Navbar from './components/Navbar';
import {BrowserRouter as Router, Switch, Route} from
'react-router-dom' 
//import Home from './pages';
import Register from './pages/register';
import Login from './pages/login';
import Admin from './pages/admin';
import Home from './pages/home';
import AddFilm from './pages/addFilm';


function App() {
  return (
    <Router>
      <Navbar />
      <Switch>
        <Route path='/register' exact component={Register} />
        <Route path='/addFilm' exact component={AddFilm} />
        <Route path='/login' exact component={Login} />
        <Route path='/admin' exact component={Admin} />
        <Route path='/home' exact component={Home} />
      </Switch>
    </Router>
  );
}

export default App;
