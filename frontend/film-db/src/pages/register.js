import React, { useState } from 'react';
import './register.css';
import API from '../api';
import Select from 'react-select';

const Register = () => {

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
        user:"",
        pCo:"",
        phNum:""
    });

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

    function getOpts(obj) {
        var opts=[];
        
        for(var i = 0; i < obj.length;i++) {
            var name = obj[i];
            opts[i] = { value: name, label: name };
        }
        return opts;
    };

    const customStyles = {
        
        option: (provided) => ({
          ...provided,
          borderBottom: '1px dotted pink',
          color: "black",
          boxShadow: "none",
          borderColor: 'gray',
          '&:hover': { borderColor: 'gray' },
          backgroundColor: "rgb(233, 234, 236,0.5)",          
        }),
        control: (provided) => ({
            ...provided,
            borderTop: '4px solid black',
            borderColor: 'gray',
            color: "black",
            '&:hover': { borderColor: 'gray' },
            boxShadow: "none",
            backgroundColor: "rgb(233, 234, 236,0.5)",          
        })
    }
    
    const genders = ["Prefer not to say","Female","Male","Non-Binary","Transgender","Intersex"];
    const countries = ["Australia","UK","Indonesia","Senegal","Uruguay"];
    const userTypes = [{ value: "1", label: "Basic User" },{ value: "2", label: "Production Company" },{ value: "3", label: "Critic" }];

    function handleSubmit() {
        var req = {
            "username": state.userName,
            "first_name": state.first,
            "last_name": state.last,
            "password": state.pw,
            "email": state.email,
            "country": state.country,
            "gender": state.gender,
            "zip_code": state.zip_code,
            "birth_year": state.year,
            "user_type": state.user,
            "production_company":state.pCo,
            "phone_number": state.phNum
        }
        API.post(`register`,req).then(res => {
            console.log(res);
        }).catch(err =>{
            console.log(err);
        })
    };
    
    return (
        <div> 
            <header className="Reg-Header">
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
                        options={getOpts(genders)} 
                        onChange={opt => setState({...state, gender: opt.value})}/>
                    <Select 
                        styles={customStyles}
                        placeholder="Country of Birth"
                        options={getOpts(countries)} 
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
                            options={getPCOS()} 
                            onChange={opt => setState({...state, pCo: opt.value})}/>
                        :null
                    }  
                    {
                        state.user.match("3")?
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
                        value="SUBMIT" 
                    />
                </div>
            </header>
        </div>
    )
}

export default Register