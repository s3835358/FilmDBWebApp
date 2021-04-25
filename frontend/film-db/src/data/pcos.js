import API from '../api'

// Get request instantiates objct containing labels and values of production companies
export const pCos = () => {
  var arr=[];
  API.get(`get-pcos`).then(res => {
      var len = res.data['pcos'].length;
      for(var i = 0; i < len;i++) {
          var name = res.data['pcos'][i].name;
          var id = res.data['pcos'][i].id;
          arr.push({ value: id, label: name });
      }
  })
  return (
    arr
  )
}
