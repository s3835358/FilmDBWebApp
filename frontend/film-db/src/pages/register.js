import React, { useState, useContext , useMemo } from 'react';
import './register.css';
import API from '../api';
import Select from 'react-select';
import {customStyles} from '../components/selectStyle';
import {DataContext} from '../data/DataContext'
import countryList from 'react-select-country-list';

const Register = () => {

    const {pcos} = useContext(DataContext);
    // Load countries from react-select-country-list
    const countries = useMemo(() => countryList().getData(), [])

    const [state,setState] = useState({
        first:"",
        last:"",
        email:"",
        userName:"",
        pw:"",
        pw_repeat:"",
        year:"",
        gender:"",
        country:"",
        zip_code:"",
        user:"1",
        pCo:"1",
        phNum:"",
        message:"",
    });

    // Gender options hardcoded, in future could use a getGender api
    const genders = [
    { value: "Prefer not to say", label: "Prefer not to say" },
    { value: "Female", label: "Female" },
    { value: "Male", label: "Male" },
    { value: "Transgender", label: "Transgender" },
    { value: "Non-Binary", label: "Non-Binary" },
    ];
    
    // Usertype options hardcoded, in future could use a getUserType api
    const userTypes = [{ value: "1", label: "Basic User" },
        { value: "2", label: "Production Company" },
        { value: "3", label: "Critic" }
    ];

    function handleSubmit() {

        // Checks password and confirm password fields match
        if(!state.pw.match(state.pw_repeat)){
            alert("Passwords do not match");
        } else{
            var req = {
                "username": state.userName,
                "first_name": state.first,
                "last_name": state.last,
                "password": state.pw,
                "email": state.email,
                "country": state.country,
                "gender": state.gender,
                "zip_code": state.zip_code,
                "birth_year": String(state.year),
                "user_type": state.user,
                "production_company":String(state.pCo),
                "phone_number": state.phNum
            }
            // Post request to register account
            API.post(`register`,req).then(res => {
                setState({...state, message: res.data['message']})
                
                if(!res.data['success']){
                    alert(res.data['message']);
                }
            }).catch(err =>{
                alert("Incorrect values");
            })
        }
    };
    
    
    return (
        <div> 
            <header className="Reg-Header">
                {
                    // Instructs user their account has been created, otherwise displays form
                    state.message.match("Your account has been successfully created! You may login now.")?
                    <div className="Reg-Title">{state.message}</div>
                    :
                    <div style={{display:"contents"}}>
                        <div className="Reg-Title">REGISTER</div>
                        <div className="Reg-Form" onSubmit={handleSubmit}>
                            <div className = "Reg-Div">
                                <input
                                    className="Reg-In-L"
                                    value={state.first}
                                    onChange={(ev) => setState({...state, first: ev.target.value})}
                                    placeholder="First Name.."
                                    type="text"
                                />
                                <input
                                    className="Reg-In-R"
                                    value={state.last}
                                    onChange={(ev) => setState({...state, last: ev.target.value})}
                                    placeholder="Last Name.."
                                    type="text"
                                />
                            </div>
                            <div className = "Reg-Div">
                                <input 
                                    className="Reg-In-L"
                                    value={state.userName}
                                    onChange={(ev) => setState({...state, userName: ev.target.value})}
                                    placeholder="Enter Username.."
                                    type="text" 
                                />
                                <input 
                                    className="Reg-In-R"
                                    value={state.email}
                                    onChange={(ev) => setState({...state, email: ev.target.value})}
                                    placeholder="example@domain.com"
                                    type="email" 
                                />
                            </div>
                            <input 
                                className="Reg-In-L"
                                value={state.pw}
                                onChange={(ev) => setState({...state, pw: ev.target.value})}
                                placeholder="Enter password.."
                                type="password" 
                            />
                            <input 
                                className="Reg-In-R"
                                value={state.pw_repeat}
                                onChange={(ev) => setState({...state, pw_repeat: ev.target.value})}
                                placeholder="Confirm password.."
                                type="password" 
                            />
                            <input 
                                className="Reg-In-L"
                                value={state.zip_code}
                                onChange={(ev) => setState({...state, zip_code: ev.target.value})}
                                placeholder="Zip Code"
                                type="tex" 
                            />
                            <input
                                className="Reg-In-R"
                                value={state.year}
                                onChange={(ev) => setState({...state, year: ev.target.value})}
                                placeholder="1990"
                                type="number"
                                min="1901"
                                max="2003"
                                name="year"
                                id="year"
                            />
                            <Select 
                                styles={customStyles}
                                className = "Reg-Sel"
                                placeholder="Gender"
                                options={genders}
                                onChange={opt => setState({...state, gender: opt.value})}/>
                            <Select 
                                styles={customStyles}
                                placeholder="Country of Birth"
                                options={countries} 
                                onChange={opt => setState({...state, country: opt.value})}/>
                            <Select 
                                styles={customStyles}
                                placeholder="User Type"
                                options={userTypes} 
                                onChange={opt => setState({...state, user: opt.value})}/>
                            {
                                state.user.match("2")?
                                <Select 
                                    styles={customStyles}
                                    placeholder="Production Company"
                                    options={pcos} 
                                    onChange={opt => setState({...state, pCo: opt.value})}/>
                                :null
                            }  
                            {
                                state.user.match("3") || state.user.match("2")?
                                <input
                                    value={state.phNum}
                                    onChange={(ev) => setState({...state, phNum: ev.target.value})}
                                    className="Reg-In-F"
                                    placeholder="0400123456"
                                    type="tel"
                                />
                                :null
                            }
                            <input 
                                className="Reg-Submit"
                                onClick={handleSubmit}
                                type="submit" 
                                value="SIGN UP" 
                            />
                        </div>
                    </div>
                }
            </header>
        </div>
    )
}

export default Register