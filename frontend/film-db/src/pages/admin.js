import React, { useState, useContext, useEffect, useMemo} from 'react'

import Table from '../components/table'
import Select from 'react-select'
import API from '../api';
import './admin.css'
import {UserContext} from '../data/UserContext';
import {DataContext} from '../data/DataContext';
import {customStyles} from '../components/selectStyle';

export const Admin = () => {

    const {user, token} = useContext(UserContext);
    const {acctData,setAcctData} = useContext(DataContext);
    const {mediaData, setMediaData} = useContext(DataContext);
    const {filmData,setFilmData} = useContext(DataContext);
    const {pcos} = useContext(DataContext);
    const {genres} = useContext(DataContext);
    const [response,setResponse] = useState("");
    const [userOpts,setUserOpts] = useState([]);
    const [mediaOpts,setMediaOpts] = useState([]);
    const [filmOpts,setFilmOpts] = useState([]);
    
    const [loaded,setLoaded] = useState(false);

    const [state,setState] = useState({
        acctSelected:"",
        mediaSelected:"",
        filmSelected:-1,
        title:"",
        length:"",
        year:"",
        director:"",
        type:"",
        pCo:"",
        genre:"",
        adminUser:"caramel6"
    })

    // Approves a specific account request as selected by the dropdown menu
    function approve(){
        API.post(`admin/approve-account-request`,{token:token, username: state.acctSelected}).then(res => {
            setResponse(res.data["message"])
        }).catch(err =>{
            alert(err);
        })
    }

    // Rejects a specific account request as selected by the dropdown menu
    function reject(){
        API.post(`admin/reject-account-request`,{token:token, username: state.acctSelected}).then(res => {
            setResponse(res.data["message"])
        }).catch(err =>{
            alert(err);
        })
    }

    function approveMedia(){
        API.post(`admin/approve-pending-show`,{token:token, show_id: state.mediaSelected}).then(res => {
            setResponse(res.data["message"])
        }).catch(err =>{
            alert(err);
        })
    }

    function rejectMedia(){
        API.post(`admin/reject-pending-show`,{token:token, show_id: state.mediaSelected}).then(res => {
            setResponse(res.data["message"])
            
        }).catch(err =>{
            alert(err);
        })
    }

    function fillDropDown(res, type){
        var len = res.length;
        var arr = [];
        for(var i = 0; i < len;i++){
            var name = "";
            var val = "";
            if(type.match('user')){
                name = res[i].username;
                val = name;
            } else if (type.match('media')) {
                name = res[i].show_title;
                val = res[i].show_id;
            } else {
                name = res[i].title;
                val = res[i].id;
            }
            arr.push({value : val, label: name});
        };
        if(type.match('user')){
            setUserOpts(arr);
        } else if (type.match('media')) {
            setMediaOpts(arr);
        } else {
            setFilmOpts(arr);
        }
        
    }
    
    function getShows(){
        if(!loaded){
            API.get(`get-shows`).then(res=>{
              setFilmData(res.data['shows'])
              setLoaded(true);
            })
            .catch(err=>{
              console.log(err);
            })
        }
    }
    

    // Updates UI based on changes to dependant data
    useEffect(() => {
        (async () => {
            if(user.match(state.adminUser)){
                // Gets requests and passes data to table
                const acctResult = await API.post(`admin/get-account-requests`,{token:token});
                setAcctData(acctResult.data['requests']);
                const mediaResult = await API.post(`admin/get-pending-shows`,{token:token});
                setMediaData(mediaResult.data['pending']);
                
                getShows();

                // Fills dropdown with usernames of those requesting accounts
                fillDropDown(acctResult.data['requests'],'user');
                fillDropDown(mediaResult.data['pending'],'media');
                fillDropDown(filmData,'films');

                

            }
        })();
    }, [response, userOpts, setAcctData, token, user, state.adminUser, setMediaData]);

    

    const acctCols = useMemo(
        () => [
          {Header: 'User Type', accessor: 'user_type',},
          {Header: 'Name', accessor: 'name',},
          {Header: 'Email', accessor: 'email',},
          {Header: 'User Name', accessor: 'username',},
          {Header: 'Production Company', accessor: 'proco_name',},
        ],
        []
    )

    const mediaCols = useMemo(
        () => [
          {Header: 'Title', accessor: 'show_title',},
          {Header: 'Genre', accessor: 'genre',},
          {Header: 'Length', accessor: 'length',},
          {Header: 'Media', accessor: 'type',},
          {Header: 'Production Company', accessor: 'proco_id',},
          {Header: 'Year', accessor: 'year',},
        ],
        []
    )
    
    return (
        <div>
        {!user.match(state.adminUser)? 
            <div> You do not have permission to view this page </div>
        :
        <div>
            
            <Table columns={acctCols} data={acctData} />
            <div className="Admin-Form">
                <Select 
                    styles={customStyles}
                    placeholder="Select User"
                    options={userOpts} 
                    onChange={opt => setState({...state, acctSelected: opt.value})}
                />
                <br/>
                <input 
                    className="Admin-Submit"
                    type="submit" 
                    onClick={approve}
                    value="APPROVE" 
                />
                <input 
                    className="Admin-Submit"
                    type="submit" 
                    onClick={reject}
                    value="REJECT" 
                />
            </div>
            <Table columns={mediaCols} data={mediaData} />
            <div className="Admin-Form">
                <Select 
                    styles={customStyles}
                    placeholder="Select Show/Film"
                    options={mediaOpts} 
                    onChange={opt => setState({...state, mediaSelected: opt.value})}
                />
                <br/>
                <input 
                    className="Admin-Submit"
                    type="submit" 
                    onClick={approveMedia}
                    value="APPROVE" 
                />
                <input 
                    className="Admin-Submit"
                    type="submit" 
                    onClick={rejectMedia}
                    value="REJECT" 
                />
            </div>
            
            <div className="Admin-Form">
                {
                    state.filmSelected == -1 ?
                    <div>
                    Select a film to edit..
                    </div>
                    :
                    <div>
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
                            options={genres}
                            onChange={opt => setState({...state, genre: opt.value})}
                        />
                        <Select 
                            styles={customStyles}
                            placeholder="Production Company"
                            options={pcos} 
                            onChange={opt => setState({...state, pCo: opt.value})}
                        />
                    </div>
                }
                <Select 
                    styles={customStyles}
                    placeholder="Select Show/Film"
                    options={filmOpts} 
                    onChange={opt => setState({...state, filmSelected: opt.value})}
                />
                <br/>
                <input 
                    className="Admin-Submit"
                    type="submit" 
                    onClick={approveMedia}
                    value="APPROVE" 
                />
                <input 
                    className="Admin-Submit"
                    type="submit" 
                    onClick={rejectMedia}
                    value="REJECT" 
                />
                

            </div>
            
        </div>
        }
        </div>
    )
}

export default Admin;