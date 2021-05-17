import API from '../api'

// Get request instantiates objct containing labels and values of production companies
export const genreFunc = () => {
  var genres=[];
  API.get(`get-genres`).then(res => {
    var len = res.data['genres'].length;
    for(var i = 0; i < len;i++) {
        var name = res.data['genres'][i].name;
        var id = res.data['genres'][i].id;
        genres[i] = { value: id, label: name };
    }
  })
  return (
    genres
  )
}