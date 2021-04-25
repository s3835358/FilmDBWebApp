import React, {useState} from 'react';
import './App.css';
import Navbar from './components/Navbar';
import {BrowserRouter as Router, Switch, Route, Redirect} from
'react-router-dom' 
import Register from './pages/register';
import Login from './pages/login';
import Admin from './pages/admin';
import Home from './pages/home';
import AddFilm from './pages/addFilm';
import { UserContext } from './data/UserContext';
import { DataContext } from './data/DataContext';
import {pCos} from './data/pcos';



function App() {
  
  const [user,setUser] = useState("Guest");
  const [token,setToken] = useState("");
  const [pcos,setPcos] = useState(pCos);
  const [data,setData] = useState([]);
  
  return (
      /*
       * Context objects pass/provide data to the components
       * Router provides functionality for navigation bar (Navbar)
       * to switch between pages within a 'single page app'
       */
      <UserContext.Provider value = {{user, token, setUser, setToken}}>
        <DataContext.Provider value = {{data, pcos, setData, setPcos}}>
        <Router>
          <Navbar/>
          <Switch>
            <Route exact path="/">
                <Redirect to="/home"/>
            </Route>
            <Route path='/home' exact component={Home} />
            <Route path='/register' exact component={Register} />
            <Route path='/addFilm' exact component={AddFilm} />
            <Route path='/login' exact component={Login} />
            <Route path='/admin' exact component={Admin} />
          </Switch>
        </Router>
        </DataContext.Provider>
      </UserContext.Provider>
  );
}

export default App;
