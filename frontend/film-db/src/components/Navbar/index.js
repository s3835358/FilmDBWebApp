import React, { useContext } from 'react';
import {Nav, NavLink,NavMenu} from './NavBarElements';
import './Nav.css';
import {UserContext} from '../../data/UserContext';


// Defines our Navbar or navigational menu component

const Navbar = () => {

  const {user, token, setUser, setToken} = useContext(UserContext);

  function handleClick(){
    setUser("Guest");
    setToken("");
  }

  

  return (
    <div >
      <Nav >
        <NavMenu >
          <NavLink className="menu-items" to="/home" activeStyle={{color:'black', background:'white'}} style={{
          textDecoration:'none',
          color:'white'
          }}>
            Home
          </NavLink>
          <NavLink className="menu-items" to="/addFilm" activeStyle={{color:'black', background:'white'}} style={{
          textDecoration:'none',
          color:'white'
          }}>
            Films
          </NavLink>
          {
            /*
             * Shows/hides our Admin page depending on whether the user is logged in or not
             * moving forward we could define a getAdmin(s) api to distinguish admin from user
             */

            !user.match("Guest")?
            <NavLink className="menu-items" to="/admin" activeStyle={{color:'black', background:'white'}} style={{
              textDecoration:'none',
              color:'white'
              }}>
                Admin
              </NavLink>
            : 
            <div>{token}</div>
            
          }
          {user.match("Guest")? 
          <div className="ml-auto">
            <NavLink className="credButton" to="/login" activeStyle={{color:'black', background:'white'}} style={{
            textDecoration:'none',
            color:'white'
            }}>
              Log In
            </NavLink>
            
            <NavLink className="credButton2" to="/register" activeStyle={{color:'black', background:'white'}} style={{
            textDecoration:'none',
            color:'white'
            }}>
              Sign Up
            </NavLink>
          </div>
          :
          <div className="ml-auto" style={{display:"flex",justifyContent:"space-between", backgroundColor:"black"}}> 

            <div className="credButton" style={{ color:"white", paddingTop:"2%", fontSize: "calc(8px + 2vmin)"}}>Welcome {user}</div>
            <input className="credButton2" style={{color:"white", paddingTop:"2%", border:"none"}}type="button" value="LOG OUT" onClick={handleClick}/>
          
          </div>
          }
        </NavMenu>
      </Nav>
    </div>
  );
};

export default Navbar;