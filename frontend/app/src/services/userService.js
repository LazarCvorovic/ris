import axios from 'axios';

const API_URL = 'http://localhost:8080/uporabniki';

const login = async (email, geslo) => {
    const response = await axios.post(`${API_URL}/login/${email}/${geslo}`);
    return response.data;
};

const getUserByEmail = async (email) => {
    const response = await axios.get(`${API_URL}/email/${email}`);
    return response.data[0];
};

export default {
    login,
    getUserByEmail,
};
