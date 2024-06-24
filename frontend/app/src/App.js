import React, { useState } from 'react';
import { Routes, Route, Link, Navigate, useNavigate } from 'react-router-dom';
import Login from './components/Login';
import Register from './components/Register';
import CreateOglas from './components/CreateOglas';
import AllOglas from './components/AllOglas';
import Profile from './components/Profile';
import './App.css';

const App = () => {
    const [isAuthenticated, setIsAuthenticated] = useState(!!localStorage.getItem('email'));
    const navigate = useNavigate();

    const handleLogin = () => {
        setIsAuthenticated(true);
    };

    const handleLogout = () => {
        localStorage.removeItem('email');
        setIsAuthenticated(false);
        navigate('/login');
    };

    return (
        <div>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="container-fluid">
                    <Link className="navbar-brand" to="/">Build My Crib</Link>
                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>
                    <div className="collapse navbar-collapse" id="navbarNav">
                        <ul className="navbar-nav">
                            <li className="nav-item">
                                <Link className="nav-link" to="/">Home</Link>
                            </li>
                            {isAuthenticated ? (
                                <>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/create-oglas">Create Oglas</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/all-oglasi">All Oglasi</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/profile">Profile</Link>
                                    </li>
                                    <li className="nav-item">
                                        <button className="nav-link btn" onClick={handleLogout}>Logout</button>
                                    </li>
                                </>
                            ) : (
                                <>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/login">Login</Link>
                                    </li>
                                    <li className="nav-item">
                                        <Link className="nav-link" to="/register">Register</Link>
                                    </li>
                                </>
                            )}
                        </ul>
                    </div>
                </div>
            </nav>
            <div className="container mt-4">
                <Routes>
                    <Route path="/login" element={<Login onLogin={handleLogin} />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/create-oglas" element={isAuthenticated ? <CreateOglas /> : <Navigate to="/login" />} />
                    <Route path="/all-oglasi" element={isAuthenticated ? <AllOglas /> : <Navigate to="/login" />} />
                    <Route path="/profile" element={isAuthenticated ? <Profile /> : <Navigate to="/login" />} />
                    <Route path="/" element={<Home />} />
                </Routes>
            </div>
        </div>
    );
};

const Home = () => (
    <div className="home-container">
        <h2>Welcome to Build My Crib</h2>
        <p>
            Build My Crib is a platform designed to connect people looking for various construction and maintenance services with service providers efficiently and quickly. Whether you are renovating an existing home or seeking professionals for maintenance work, Build My Crib simplifies the process, making it easier for service seekers and providers to connect and collaborate.
        </p>
    </div>
);

export default App;
