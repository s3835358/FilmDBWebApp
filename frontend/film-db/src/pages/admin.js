import React, { useState, useContext} from 'react'
import styled from 'styled-components'
import {useTable} from 'react-table'
import Select from 'react-select'
import API from '../api';
import './admin.css'
import {UserContext} from '../UserContext';
import {PcoContext} from '../PcoContext';

export const Admin = () => {

    const {user, token, setUser, setToken} = useContext(UserContext);
    const {pco, setPco} = useContext(PcoContext);
    const [data, setData] = useState([]);

    const Styles = styled.div`
        padding: 1rem;

        table {
            border-spacing: 0;
            border: 1px solid black;
            width:100%;

            tr {
            :last-child {
                td {
                border-bottom: 0;
                }
            }
            }

            th,
            td {
            margin: 0;
            padding: 0.5rem;
            border-bottom: 1px solid black;
            border-right: 1px solid black;

            :last-child {
                border-right: 0;
            }
            }
        }
    `

    const [state,setState] = useState({
      selected:"",
      requests:[],
    })

    function approve(){
        API.post(`admin/approve-account-request`,{token:token, username: state.selected}).then(res => {
            console.log(res);
          }).catch(err =>{
              console.log(err);
        })
    }
    function reject(){
        API.post(`admin/reject-account-request`,{token:token, username: state.selected}).then(res => {
            console.log(res);
          }).catch(err =>{
              console.log(err);
        })
    }
    const Users = ["User 1","User 2","User 3","User 4","User 5"];
    

    function getOpts() {
        var arr =[];
        for(var i = 0; i <data.length;i++) {
            arr.push(data[i].username);
        }
        var opts=[];
        
        for(var i = 0; i < arr.length;i++) {
            var name = arr[i];
            opts[i] = { value: name, label: name };
        }
        
        return opts;
    };

    const customStyles = {
        
      option: (provided) => ({
        ...provided,
        border: 'none',
        color: "black",
        boxShadow: "none",
        borderColor: 'gray',
        '&:hover': { border: '1px solid gray' },
        backgroundColor: "rgb(233, 234, 236,0.5)",          
      }),
      control: (provided) => ({
          ...provided,
          border: 'none',
          borderColor: 'gray',
          color: "black",
          '&:hover': { borderColor: 'gray' },
          boxShadow: "none",
          backgroundColor: "rgb(233, 234, 236,0.5)",          
      })
  }


    function request(){ 
        API.post(`admin/get-account-requests`,{token:token}).then(res => {
          console.log(res);
          var len = res.data['requests'].length;
          for(var i = 0; i < len;i++){
            //When is REQUESTS used?
            state.requests.push(res.data['requests'][i])
          }
          setData(res.data['requests']);
        }).catch(err =>{
            console.log(err);
        })
        console.log(token);
        console.log(state.requests);
    }



    
    const columns = React.useMemo(
        () => [
          {Header: 'User Type', accessor: 'user_type',},
          {Header: 'Name', accessor: 'name',},
          {Header: 'Email', accessor: 'email',},
          {Header: 'User Name', accessor: 'username',},
          {Header: 'Production Company', accessor: 'proco_name',},
        ],
        []
    )
    
    const tableInstance = useTable({ columns, data })


    const {
        getTableProps,
        getTableBodyProps,
        headerGroups,
        rows,
        prepareRow,
    } = tableInstance

    return (
      <div>
        
          <Styles>
              <table {...getTableProps()}>
                  <thead>
                  {// Loop over the header rows
                  headerGroups.map(headerGroup => (
                      // Apply the header row props
                      <tr {...headerGroup.getHeaderGroupProps()}>
                      {// Loop over the headers in each row
                      headerGroup.headers.map(column => (
                          // Apply the header cell props
                          <th {...column.getHeaderProps()}>
                          {// Render the header
                          column.render('Header')}
                          </th>
                      ))}
                      </tr>
                  ))}
                  </thead>
                  {/* Apply the table body props */}
                  <tbody {...getTableBodyProps()}>
                  {// Loop over the table rows
                  rows.map(row => {
                      // Prepare the row for display
                      prepareRow(row)
                      return (
                      // Apply the row props
                      <tr {...row.getRowProps()}>
                          {// Loop over the rows cells
                          row.cells.map(cell => {
                          // Apply the cell props
                          return (
                              <td {...cell.getCellProps()}>
                              {// Render the cell contents
                              cell.render('Cell')}
                              </td>
                          )
                          })}
                      </tr>
                      )
                  })}
                  </tbody>
              </table>
          </Styles>
          <div className="Admin-Form">
            <Select 
              styles={customStyles}
              placeholder="Add User"
              options={getOpts(Users)} 
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
          <input type="button" value="Click me" onClick={request}></input>
          <div></div>
      </div>
    )
}

export default Admin;