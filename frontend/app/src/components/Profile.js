import React, { useEffect, useState } from 'react';
import uporabnikService from '../services/uporabnikService';

const Profile = () => {
    const [profile, setProfile] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchProfile = async () => {
            const email = localStorage.getItem('email');
            if (email) {
                try {
                    const response = await uporabnikService.getProfile(email);
                    setProfile(response.data);
                } catch (error) {
                    setError('Failed to fetch profile');
                }
            } else {
                setError('No email found in localStorage');
            }
        };
        fetchProfile();
    }, []);

    if (error) {
        return <div>{error}</div>;
    }

    if (!profile) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>Profile</h1>
            <p><strong>Name:</strong> {profile.ime}</p>
            <p><strong>Last Name:</strong> {profile.priimek}</p>
            <p><strong>Email:</strong> {profile.email}</p>
            <p><strong>Address:</strong> {profile.adresa}</p>
            <p><strong>Phone:</strong> {profile.telefonskaStevilka}</p>
        </div>
    );
};

export default Profile;
