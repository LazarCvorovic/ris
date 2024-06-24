import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import authService from '../services/authService';
import '../css/Register.css';

const Register = () => {
    const [user, setUser] = useState({
        ime: '',
        priimek: '',
        adresa: '',
        telefonskaStevilka: '',
        oglasavanje: false,
        email: '',
        geslo: ''
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setUser({
            ...user,
            [name]: type === 'checkbox' ? checked : value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await authService.register(user);
            alert('Registration successful');
            navigate('/login'); // Redirect to login page
        } catch (error) {
            alert('Registration failed');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <h2>Register</h2>
            <input
                type="text"
                name="ime"
                value={user.ime}
                onChange={handleChange}
                placeholder="First Name"
                required
                className="input-field"
            />
            <input
                type="text"
                name="priimek"
                value={user.priimek}
                onChange={handleChange}
                placeholder="Last Name"
                required
                className="input-field"
            />
            <input
                type="text"
                name="adresa"
                value={user.adresa}
                onChange={handleChange}
                placeholder="Address"
                required
                className="input-field"
            />
            <input
                type="text"
                name="telefonskaStevilka"
                value={user.telefonskaStevilka}
                onChange={handleChange}
                placeholder="Phone Number"
                required
                className="input-field"
            />
            <label className="checkbox-container">
                <input
                    type="checkbox"
                    name="oglasavanje"
                    checked={user.oglasavanje}
                    onChange={handleChange}
                />
                Advertising
            </label>
            <input
                type="email"
                name="email"
                value={user.email}
                onChange={handleChange}
                placeholder="Email"
                required
                className="input-field"
            />
            <input
                type="password"
                name="geslo"
                value={user.geslo}
                onChange={handleChange}
                placeholder="Password"
                required
                className="input-field"
            />
            <button type="submit" className="submit-button">Register</button>
        </form>
    );
};

export default Register;
