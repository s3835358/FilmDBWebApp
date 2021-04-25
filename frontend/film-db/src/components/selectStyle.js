export const customStyles = {
        
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
