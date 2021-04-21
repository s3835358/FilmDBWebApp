import React, { useState } from 'react'
import './addFilm.css'

const AddFilm = () => {

    const [state,setState] = useState({
        title:"",
        length:null,
        releaseDate:"Year-Month-Day",
        director:"",
        actors:[],
        pCo:"",
        genre:"",
    });

    function handleSubmit() {
        alert(state.title + " " +
            state.length + " " +
            state.releaseDate +" " +
            state.director +" " +
            state.actors +" " +
            state.pCo +" " +
            state.genre +" "
            );
    };
    return (
        <div> 
            <header className="Film-Header">
                <form className="Film-Form" onSubmit={handleSubmit}>
                    
                    <input
                        className="Film-In"
                        value={state.title}
                        onChange={(ev) => setState({...state, title: ev.target.value})}
                        placeholder="Film Title.."
                        type="text"
                    />
                    <input
                        className="Film-In"
                        value={state.director}
                        onChange={(ev) => setState({...state, director: ev.target.value})}
                        placeholder="Director Name.."
                        type="text"
                    />
                    <input 
                        className="Film-In"
                        value={state.pCo}
                        onChange={(ev) => setState({...state, pCo: ev.target.value})}
                        placeholder="Production Company.."
                        type="text" 
                    />
                    <input 
                        className="Film-In"
                        value={state.length}
                        onChange={(ev) => setState({...state, length: ev.target.value})}
                        placeholder="Film Length (minutes).."
                        type="text" 
                    />
                    <input
                        className="Film-In"
                        value={state.releaseDate}
                        onChange={(ev) => setState({...state, releaseDate: ev.target.value})}
                        placeholder="1990"
                        type="date"
                        min="1800-01-01"
                        max="2025-01-01"
                        name="date"
                        id="date"
                    />
                    <select required
                        value={state.genre} 
                        onChange={(ev) => setState({...state, genre: ev.target.value})}
                        className="Film-Sel"
                        name="Gender" 
                        id="gender">
                        <option value="" disabled selected>Select Genre..</option>
                        <option value="Action">Action</option>
                        <option value="Animation">Animation</option>
                        <option value="Adventure">Adventure</option>
                        <option value="Comedy">Comedy</option>
                        <option value="Crime">Crime</option>
                        <option value="Documentary">Documentary</option>
                        <option value="Drama">Drama</option>
                        <option value="Horror">Horror</option>
                        <option value="Period">Period</option>
                        <option value="Romance">Romance</option>
                        <option value="Sci-Fi">Sci-Fi</option>
                        <option value="Thriller">Thriller</option>
                    </select>
                    <input 
                        className="Film-Submit"
                        type="submit" 
                        value="ADD FILM" 
                    />
                </form>
            </header>
        </div>
    )
}

export default AddFilm