import React, {useState} from 'react';
import './App.css';
import Navbar from './components/Navbar';
import {BrowserRouter as Router, Switch, Route} from
'react-router-dom' 
import Register from './pages/register';
import Login from './pages/login';
import Admin from './pages/admin';
import Home from './pages/home';
import AddFilm from './pages/addFilm';
import { UserContext } from './UserContext';
import { PcoContext } from './PcoContext';
import API from './api'

function getPCOS() {
  var pcos=[];
  API.get(`get-pcos`).then(res => {
      var len = res.data['pcos'].length;
      for(var i = 0; i < len;i++) {
          var name = res.data['pcos'][i].name;
          pcos[i] = { value: name, label: name };
      }
  })
  return pcos;
};

function App() {
  
  const [user,setUser] = useState("Guest");
  const [token,setToken] = useState("abc");
  const [pco,setPco] = useState(getPCOS);

  

  

  return (
    
    <PcoContext.Provider value= {{pco,setPco}}>
      <UserContext.Provider value = {{user, token, setUser, setToken}}>
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
      </UserContext.Provider>
    </PcoContext.Provider>
  );
}

export default App;
