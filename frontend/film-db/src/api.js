import axios from 'axios';

export default axios.create({
  baseURL: `http://45.77.232.166:8080/`
});

/*export const PCOS =  api.get('/get-pcos').then(res => {
    var len = res.data['pcos'].length;
    for (var i = 0; i < len; i++) {
      setPCOS(current => [...current, res.data['pcos'][i].name]);
      console.log(pcos[i]);
   }
  })
  return(
    pcos
  );
}*/
