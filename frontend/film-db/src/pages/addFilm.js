import React, { useState, useContext } from 'react';
import './addFilm.css';
import API from '../api';
import Select from 'react-select';
import {UserContext} from '../UserContext';

const AddFilm = () => {

    const [state,setState] = useState({
        title:"",
        length:"",
        year:"",
        director:"",
        type:"",
        pCo:"",
        genre:"",
    });

    const {user, token, setUser, setToken} = useContext(UserContext);

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


    function handleSubmit() {
        var req = {
            // UPDATE
            token:token,
            title:state.title,
            genre:String(state.genre),
            length:String(state.length),
            type: String(state.type),
            year: String(state.year),
            proco_id: String(state.pCo),
        }
        alert(
            token+ " " +
            state.title + " " +
            state.genre + " " +
            state.length + " " +
            state.type + " " +
            state.year +" " +
            state.pCo +" "
        );
        API.post(`show/add`, req).then(res => {
            console.log(res.data)
        })
    };

    function getGenres() {
        var genres=[];
        API.get(`get-genres`).then(res => {
            var len = res.data['genres'].length;
            for(var i = 0; i < len;i++) {
                var name = res.data['genres'][i].name;
                var id = res.data['genres'][i].id;
                genres[i] = { value: id, label: name };
            }
        })
        return genres;
    };

    function getPCOS() {
        var pcos=[];
        API.get(`get-pcos`).then(res => {
            var len = res.data['pcos'].length;
            for(var i = 0; i < len;i++) {
                var name = res.data['pcos'][i].name;
                var id = res.data['pcos'][i].id;
                pcos[i] = { value: id, label: name };
            }
        })
        return pcos;
    };

    return (
        <div> 
            <header className="Film-Header">
                <div className="Film-Form">
                    
                    <input
                        className="Film-In"
                        value={state.title}
                        onChange={(ev) => setState({...state, title: ev.target.value})}
                        placeholder="Film Title.."
                        type="text"
                    />
                    <input 
                        className="Film-In"
                        value={state.length}
                        onChange={(ev) => setState({...state, length: ev.target.value})}
                        placeholder="Length.."
                        type="text" 
                    />
                    <input
                        className="Film-In"
                        value={state.year}
                        onChange={(ev) => setState({...state, year: ev.target.value})}
                        placeholder="1990"
                        type="number"
                        min="1850"
                        max="2025"
                        name="year"
                        id="year"
                    />
                    <Select 
                        styles={customStyles}
                        placeholder="Format.."
                        options={[{ value: "MOVIE", label: "MOVIE" },{ value: "SERIES", label: "SERIES" }]}
                        onChange={opt => setState({...state, type: opt.value})}
                    />
                    <Select 
                        styles={customStyles}
                        placeholder="Genre.."
                        options={getGenres()}
                        onChange={opt => setState({...state, genre: opt.value})}
                    />
                    <Select 
                            styles={customStyles}
                            placeholder="Production Company"
                            options={getPCOS()} 
                            onChange={opt => setState({...state, pCo: opt.value})}
                    />
                    <input 
                        className="Film-Submit"
                        type="submit" 
                        onClick={handleSubmit}
                        value="ADD FILM" 
                    />
                </div>
            </header>
        </div>
    )
}

export default AddFilm