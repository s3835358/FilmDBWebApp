import React, { Component } from 'react';
import './Form.css';

class Form extends Component {
    
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: ''};
        this.emailChange = this.emailChange.bind(this);
        this.passwordChange = this.passwordChange.bind(this);
    }

    emailChange(event) {
        
        this.setState({email: event.target.value});
    }

    passwordChange(event) {
        
        this.setState({password: event.target.value});
    }

    render() {
        return(
            <div className="Form">
                <header className="Form-Header">
                    
                    <form className="Form-Form">
                        <input 
                            className="Input-Email"
                            placeholder="Email: example@domain.com"
                            type="text" 
                            value={this.state.email}
                            onChange={this.emailChange} 
                        />
                        
                        <input 
                            className="Input-Password"
                            placeholder="Enter password"
                            type="password" 
                            value={this.state.password}
                            onChange={this.passwordChange} 
                        />
                        
                        <input 
                            className="Form-Submit"
                            type="submit" 
                            value="SUBMIT" 
                        />
                    </form>
                </header>
            </div>
        );
    }
}

export default Form; 