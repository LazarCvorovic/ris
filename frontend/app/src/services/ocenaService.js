import axios from 'axios';

const API_URL = 'http://localhost:8080/ocene';

const getOceneByOglas = (oglasId) => {
    return axios.get(`${API_URL}/${oglasId}`)
        .then(response => response.data);
};

const addOcena = (ocena) => {
    return axios.post(API_URL, ocena)
        .then(response => response.data);
};

export default {
    getOceneByOglas,
    addOcena
};
