import React, { useState, useContext } from 'react';
import './login.css';
import API from '../api';
import {UserContext} from '../UserContext';


const Login = () => {
    
    const [state,setState] = useState({
        userName: '',
        password: '',
        loggedIn: 'false',
        token:'',
    });

    const {user, token, setUser, setToken} = useContext(UserContext);
    
    function handleSubmit() {
        var req = {
            "username": state.userName,
            "password": state.password,
        }
        API.post(`login`,req).then(res => {
            console.log(res);
            if(res.data.success) {
                //loggedInChange();
                setState({...state, token: String(res.data.token)})
                setToken(String(res.data.token))
                setUser(state.userName)
                console.log(String(res.data.token))
            }
        }).catch(err =>{
            console.log(err);
        })
    }    
    //Currently toggles
    function loggedInChange() {
        var change = "true";
        if(state.loggedIn.match("true")) {
            change="false";
        } else {
            
        }
        setState({...state, loggedIn: change});
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
                        value="SUBMIT" 
                    />
                </div>
                :
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