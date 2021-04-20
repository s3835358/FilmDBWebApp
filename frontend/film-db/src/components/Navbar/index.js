import React from 'react';
import {Nav, NavLink,NavMenu} from './NavBarElements';
import './Nav.css'

const Navbar = () => {
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
          <NavLink className="menu-items" to="/admin" activeStyle={{color:'black', background:'white'}} style={{
          textDecoration:'none',
          color:'white'
          }}>
            Admin
          </NavLink>
          <div className="ml-auto">
            <NavLink className="credButton" to="/Form" activeStyle={{color:'black', background:'white'}} style={{
            textDecoration:'none',
            color:'white'
            }}>
              Log In
            </NavLink>
            
            <NavLink className="credButton2" to="/about" activeStyle={{color:'black', background:'white'}} style={{
            textDecoration:'none',
            color:'white'
            }}>
              Sign Up
            </NavLink>
          </div>
        </NavMenu>
      </Nav>
    </div>
  );
};

export default Navbar;