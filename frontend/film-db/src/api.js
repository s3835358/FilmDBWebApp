import axios from 'axios';

// API allows for shorthand calls to our api address through axios
export default axios.create({
  baseURL: `http://45.77.232.166:8080/`
});

