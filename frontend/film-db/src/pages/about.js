import React, { useState } from 'react'
import './about.css'


const About = () => {

    const [state,setState] = useState({
        first:"",
        last:"",
        email:"",
        userName:"",
        pw:"",
        year:1990,
        gender:"",
        country:"",
        user:"",
        pCo:"",
        phNum:""
    });

    
    function handleSubmit() {
        alert(state.first + " " +
            state.last + " " +
            state.email + " " +
            state.country +" " +
            state.pw +" " +
            state.userName +" " +
            state.year +" " +
            state.gender +" " +
            state.user +" " +
            state.pCo + " " +
            state.phNum);
    };
    
    return (
        <div> 
        
            <header className="Reg-Header">
                <form className="Reg-Form" onSubmit={handleSubmit}>
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
                            value={state.email}
                            onChange={(ev) => setState({...state, email: ev.target.value})}
                            placeholder="example@domain.com"
                            type="email" 
                        />
                        <input 
                            className="Reg-In-R"
                            value={state.userName}
                            onChange={(ev) => setState({...state, userName: ev.target.value})}
                            placeholder="Enter Username.."
                            type="text" 
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
                        value={state.year}
                        onChange={(ev) => setState({...state, year: ev.target.value})}
                        placeholder="1990"
                        type="number"
                        min="1901"
                        max="2003"
                        name="year"
                        id="year"
                    />
                    
                    <select required
                        value={state.gender} 
                        onChange={(ev) => setState({...state, gender: ev.target.value})}
                        className="Reg-Sel-T"
                        name="Gender" 
                        id="gender">
                        <option value="" disabled selected>Select Gender..</option>
                        <option value="Prefer not to say">Prefer not to say</option>
                        <option value="Female">Female</option>
                        <option value="Male">Male</option>
                        <option value="Non-Binary">Non-Binary</option>
                        <option value="Transgender">Transgender</option>
                        <option value="Intersex">Intersex</option>
                    </select>
                    
                    <select required
                        value={state.country}
                        onChange={(ev) => setState({...state, country: ev.target.value})} 
                        className="Reg-Sel"
                        name="Country"
                        id="country">
                        <option value="" disabled selected>Country of residence..</option>
                        <option value="Australia">Australia</option>
                        <option value="UK">UK</option>
                        <option value="Indonesia">Indonesia</option>
                        <option value="Senegal">Senegal</option>
                        <option value="Uruguay">Uruguay</option>
                    </select>
                    <select required
                        value={state.user} 
                        onChange={(ev) => setState({...state, user: ev.target.value})}
                        className="Reg-Sel"
                        name="Type" 
                        id="type">
                        <option value="" disabled selected>User Type..</option>
                        <option 
                            value="Basic User">
                            Basic User
                        </option>
                        <option 
                            value="Production Company"> 
                            Production Company
                        </option>
                        <option 
                            value="Critic">
                            Critic
                        </option>
                    </select>
                    {
                        state.user.match("Production Company")?
                        <input
                            value={state.pCo}
                            onChange={(ev) => setState({...state, pCo: ev.target.value})}
                            className="Reg-In-F"
                            placeholder="Company Name.."
                            type="text"
                        />
                        :null
                    }  
                    {
                        state.user.match("Critic")?
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
                        type="submit" 
                        value="SUBMIT" 
                    />
                </form>
            </header>
        </div>
    )
}

export default About