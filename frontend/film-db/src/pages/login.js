import React, { useState, useContext } from 'react';
import './login.css';
import API from '../api';
import {UserContext} from '../data/UserContext';


const Login = () => {
    
    const [state,setState] = useState({
        userName: '',
        password: '',
        loggedIn: 'false',
        message:'',
    });

    const {user, setUser, setToken} = useContext(UserContext);
    
    
    function handleSubmit() {
        var req = {
            "username": state.userName,
            "password": state.password,
        }

        // Post request to api, passing our username and password as data
        API.post(`login`,req).then(res => {

            if(res.data.success) {
                setToken(String(res.data.token))
                setUser(state.userName)
            } else{
                // Tell user what went wrong with submission
                alert(res.data.message);
            }

        }).catch(err =>{
            // Tell user what went wrong with submission
            alert(err);
        })
    }    
    

    function userNameChange(event) {
        
        setState({...state, userName: event.target.value});
    }

    function passwordChange(event) {
        
        setState({...state, password: event.target.value});
    }
    
    
    return(
        <div className="Form">
            <header className="Form-Header">
                
                {user.match("Guest")?

                <div className="Form-Form">

                    <input 
                        className="Input-UserName"
                        placeholder="Username.."
                        type="text" 
                        value={state.userName}
                        onChange={userNameChange} 
                    />
                    
                    <input 
                        className="Input-Password"
                        placeholder="Password.."
                        type="password" 
                        value={state.password}
                        onChange={passwordChange} 
                    />
                    
                    <input 
                        className="Form-Submit"
                        onClick={handleSubmit}
                        type="submit" 
                        value="LOG IN" 
                    />

                    <div>{state.message}</div>

                </div>
                :
                // Prevent logged in user from attempting to log in again
                <div 
                    style={{backgroundColor:"rgb(233, 234, 236,0.75)", width:"30%",
                    height:"20%", border:"4px solid black", textAlign:"center"}}> 
                    You are currently logged in as {user}
                </div>
                } 
            </header>
        </div>
    );
}


export default Login; 