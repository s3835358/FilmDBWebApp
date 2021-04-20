import React, { Component } from 'react'
import Axios from 'axios'

const api = Axios.create({
  baseURL: `http://45.77.232.166:8080/`
})

class Test extends Component {
  
  state = {
    courses: []
  }

  constructor() {
    super();
    api.get('/get-pcos').then(res => {
      console.log(res.data)
      this.state.courses = res.data
      var obj = this.state.courses
      alert(obj[0].name)
    })
  }


  
  render() {
  return(
    <div className="Test">
      <header className = "Test-head">
        
      </header>
      <button>Get Info</button>
    </div>
  );
  }
}

export default Test; 