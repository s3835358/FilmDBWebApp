import React from 'react'
import styled from 'styled-components'
import {useTable} from 'react-table'


export const Table = () => {

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
  
  const tableInstance = useTable({ columns, request })

  const {
      getTableProps,
      getTableBodyProps,
      headerGroups,
      rows,
      prepareRow,
  } = tableInstance

  return (
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
  )
}

export default Table;