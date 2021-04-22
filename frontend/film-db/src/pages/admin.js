import React, { useState } from 'react'
import styled from 'styled-components'
import {useTable} from 'react-table'
import Select from 'react-select'


export const Admin = () => {

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
      approved:[]
    })

    const Users = ["User 1","User 2","User 3","User 4","User 5"];

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

    
    const data = React.useMemo(
        () => 
        [
          {
            colUserType: 'Basic User',colName:'Declan', colEmail:'d@e.co',colUsername: 'decFace02',colPCO: 'Miramax',
          },
          {
            colUserType: 'Basic User',colName:'Declan', colEmail:'d@e.co',colUsername: 'decFace02',colPCO: 'Miramax',
          },
          {
            colUserType: 'Basic User',colName:'Declan', colEmail:'d@e.co',colUsername: 'decFace02',colPCO: 'Miramax',
            
          },
        ],
        []
    )
    
    const columns = React.useMemo(
        () => [
          {Header: 'User Type', accessor: 'colUserType',},
          {Header: 'Name', accessor: 'colName',},
          {Header: 'Email', accessor: 'colEmail',},
          {Header: 'User Name', accessor: 'colUsername',},
          {Header: 'Production Company', accessor: 'colPCO',},
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
          <form className="Admin-Form">
            <Select 
              isMulti={true}
              styles={customStyles}
              placeholder="Add User"
              options={getOpts(Users)} 
              onChange={opt => setState({...state, approved: opt.value})}
            />
            <input 
                className="Reg-Submit"
                type="submit" 
                value="APPROVE" 
            />
          </form>
      </div>
    )
}

export default Admin;