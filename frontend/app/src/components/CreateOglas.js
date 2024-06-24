import React, { useState } from 'react';
import oglasService from '../services/oglasService';
import '../css/CreateOglas.css';

const CreateOglas = () => {
    const [oglas, setOglas] = useState({
        opis: '',
        datumOd: '',
        datumDo: '',
        adresa: '',
        otkazano: false,
        regija: '',
        naslov: '',
        telefonskaStevilka: '',
        enaslov: ''
    });

    const handleChange = (e) => {
        const { name, value, type, checked } = e.target;
        setOglas({
            ...oglas,
            [name]: type === 'checkbox' ? checked : value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await oglasService.createOglasAndGeneratePdf(oglas);
            alert('Oglas created successfully, PDF generated and email sent');
        } catch (error) {
            alert('Failed to create oglas, generate PDF, and send email');
        }
    };

    return (
        <form onSubmit={handleSubmit} className="form-container">
            <h2>Create Oglas</h2>
            <input
                type="text"
                name="opis"
                value={oglas.opis}
                onChange={handleChange}
                placeholder="Description"
                required
                className="input-field"
            />
            <input
                type="date"
                name="datumOd"
                value={oglas.datumOd}
                onChange={handleChange}
                required
                className="input-field"
            />
            <input
                type="date"
                name="datumDo"
                value={oglas.datumDo}
                onChange={handleChange}
                required
                className="input-field"
            />
            <input
                type="text"
                name="adresa"
                value={oglas.adresa}
                onChange={handleChange}
                placeholder="Address"
                required
                className="input-field"
            />
            <label className="checkbox-container">
                <input
                    type="checkbox"
                    name="otkazano"
                    checked={oglas.otkazano}
                    onChange={handleChange}
                />
                Canceled
            </label>
            <input
                type="text"
                name="regija"
                value={oglas.regija}
                onChange={handleChange}
                placeholder="Region"
                required
                className="input-field"
            />
            <input
                type="text"
                name="naslov"
                value={oglas.naslov}
                onChange={handleChange}
                placeholder="Title"
                required
                className="input-field"
            />
            <input
                type="text"
                name="telefonskaStevilka"
                value={oglas.telefonskaStevilka}
                onChange={handleChange}
                placeholder="Phone Number"
                required
                className="input-field"
            />
            <input
                type="email"
                name="enaslov"
                value={oglas.enaslov}
                onChange={handleChange}
                placeholder="Email"
                required
                className="input-field"
            />
            <button type="submit" className="submit-button">Create Oglas</button>
        </form>
    );
};

export default CreateOglas;
