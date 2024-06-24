import axios from 'axios';

const API_URL = 'http://localhost:8080/oglasi';

const getAllOglasi = () => {
    return axios.get(API_URL)
        .then(response => response.data);
};

const findByFilters = (filters) => {
    const params = new URLSearchParams();

    if (filters.regija) {
        params.append('regija', filters.regija);
    }
    if (filters.datumOd) {
        params.append('datumOd', filters.datumOd);
    }
    if (filters.datumDo) {
        params.append('datumDo', filters.datumDo);
    }
    if (filters.adresa) {
        params.append('adresa', filters.adresa);
    }

    return axios.get(`${API_URL}/search`, { params })
        .then(response => response.data);
};

const applyToOglas = (oglas) => {
    return axios.post(`${API_URL}/apply`, oglas);
};

const createOglasAndGeneratePdf = (oglas) => {
    return axios.post(`${API_URL}/pdf`, oglas);
};

const deleteOglas = (idOglas) => {
    return axios.delete(`${API_URL}/${idOglas}`);
};

export default {
    getAllOglasi,
    findByFilters,
    applyToOglas,
    createOglasAndGeneratePdf,
    deleteOglas
};
