import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import uporabnikService from '../services/uporabnikService'; // Dodali smo uvoz za uporabnikService

const Login = ({ onLogin }) => {
    const [email, setEmail] = useState('');
    const [geslo, setGeslo] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await uporabnikService.login(email, geslo);
            if (response.status === 200) {
                onLogin();
                navigate('/profile'); // Nakon uspešnog login-a prebacujemo na profil
            } else {
                setError('Login failed');
            }
        } catch (error) {
            setError('Login failed');
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={handleSubmit} className="form-container">
                <h2>Login</h2>
                {error && <p className="error">{error}</p>}
                <input
                    type="email"
                    name="email"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    placeholder="Email"
                    required
                    className="input-field"
                />
                <input
                    type="password"
                    name="geslo"
                    value={geslo}
                    onChange={(e) => setGeslo(e.target.value)}
                    placeholder="Password"
                    required
                    className="input-field"
                />
                <button type="submit" className="submit-button">Login</button>
            </form>
        </div>
    );
};

export default Login;
