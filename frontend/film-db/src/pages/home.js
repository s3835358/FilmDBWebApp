import React, { useMemo, useContext, useEffect, useState } from 'react';
import './home.css';
import Table from '../components/table';
import API from '../api';
import axios from 'axios';
import {DataContext} from '../data/DataContext';

export const Home = () => {

  const {filmData, setFilmData} = useContext(DataContext);
  const [selected, setSelected] = useState('null');
  const [filmSel, setFilmSel] = useState([]);
  const [loaded, setLoaded] = useState(false);

  const cols = useMemo(
    () => [
      {Header: 'Title', accessor: 'title',},
      {Header: 'Genre', accessor: 'genre',},
      {Header: 'Year', accessor: 'year',},
      {Header: 'Length', accessor: 'length',},
      {Header: 'Production Company', accessor: 'production_company',},
      {Header: 'Type', accessor: 'type',},
    ],
    []
  )


  useEffect(()=>{
    if(!loaded){
      API.get(`get-shows`).then(res=>{
        setFilmData(res.data['shows'])
        setLoaded(true);
        console.log("LOAD");
      })
      .catch(err=>{
        console.log(err);
      })
    }

    var str = 'http://45.77.232.166:8080/get-show/'+ selected;
    if(!selected.match('null')){
      axios({
        method: 'get',
        url: str,
        responseType: 'stream'
      }).then((response) =>{
        
        const data = response.data['show'];
        const arr = [data];
        
        setFilmSel(arr);
        console.log(arr);
      });
    }
  },[selected, loaded, setFilmSel]
  )


  return (
    <div className = "home" style={{textAlign:"center"}}>
      <div className = "home-films">
        <div className = "home-list">
          {filmData.map((data,id)=>{
            return<div>
              <div
                onClick={() => 
                  setSelected(String(data.id))
                }
              >
                {data.title} 
              </div>
            </div>  
          })}
        </div>
        {selected.match('null')? 
          <div> Select a film </div>
          :
          <div className = "home-table">
            {filmSel.title}
            <Table columns = {cols} data = {filmSel}/>
          </div>
        }
      </div>  
    </div>
  )
  
}

export default Home;
