const API_URL = 'http://localhost:8080/uporabniki';

const login = async (email, geslo) => {
    const response = await fetch(`${API_URL}/login/${email}/${geslo}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        }
    });
    if (!response.ok) {
        throw new Error('Login failed');
    }
    return response.json();
};

const register = async (user) => {
    const response = await fetch(`${API_URL}/add`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });
    if (!response.ok) {
        throw new Error('Registration failed');
    }
    return response.json();
};

export default { login, register };
