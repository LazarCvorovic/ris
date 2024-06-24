import axios from 'axios';

const API_URL = 'http://localhost:8080/uporabniki';

const login = (email, geslo) => {
    return axios.post(`${API_URL}/login/${email}/${geslo}`);
};

const getProfile = (email) => {
    return axios.get(`${API_URL}/email/${email}`);
};

const isAdmin = (email) => {
    return axios.get(`${API_URL}/isAdmin/${email}`);
};

const uporabnikService = {
    login,
    getProfile,
    isAdmin
};

export default uporabnikService;
