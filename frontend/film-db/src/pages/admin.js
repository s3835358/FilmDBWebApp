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
    const {data,setData} = useContext(DataContext);

    const [state,setState] = useState({
      selected:"",
      response:"",
      userOpts:[],
      adminUser:"caramel6"
    })

    // Approves a specific account request as selected by the dropdown menu
    function approve(){
        API.post(`admin/approve-account-request`,{token:token, username: state.selected}).then(res => {
            setState({...state, response: res.data["message"]})
        }).catch(err =>{
            alert(err);
        })
    }

    // Rejects a specific account request as selected by the dropdown menu
    function reject(){
        API.post(`admin/reject-account-request`,{token:token, username: state.selected}).then(res => {
            setState({...state, response: res.data["message"]})
        }).catch(err =>{
            alert(err);
        })
    }
    
    // Updates UI based on changes to dependant data
    useEffect(() => {
        (async () => {
            if(user.match(state.adminUser)){
                // Gets requests and passes data to table
                const result = await API.post(`admin/get-account-requests`,{token:token});
                setData(result.data['requests']);

                // Fills dropdown with usernames of those requesting accounts
                var len = result.data['requests'].length;
                var arr = [];
                for(var i = 0; i < len;i++){
                    var name = result.data['requests'][i].username;
                    arr.push({value : name, label: name});
                };
                setState({...state, userOpts: arr});
            }
        })();
    }, [state.response]);

    

    const columns = useMemo(
        () => [
          {Header: 'User Type', accessor: 'user_type',},
          {Header: 'Name', accessor: 'name',},
          {Header: 'Email', accessor: 'email',},
          {Header: 'User Name', accessor: 'username',},
          {Header: 'Production Company', accessor: 'proco_name',},
        ],
        []
    )
    
    return (
        <div>
        {!user.match(state.adminUser)? 
            <div> You do not have permission to view this page </div>
        :
        <div>
            
            <Table columns={columns} data={data} />
            <div className="Admin-Form">
                <Select 
                    styles={customStyles}
                    placeholder="Select User"
                    options={state.userOpts} 
                    onChange={opt => setState({...state, selected: opt.value})}
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
            
        </div>
        }
        </div>
    )
}

export default Admin;