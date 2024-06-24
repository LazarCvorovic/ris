import React, { useState, useEffect } from 'react';
import oglasService from '../services/oglasService';
import uporabnikService from '../services/uporabnikService';
import ocenaService from '../services/ocenaService';
import '../css/AllOglas.css';

const AllOglas = () => {
    const [oglasi, setOglasi] = useState([]);
    const [filters, setFilters] = useState({
        regija: '',
        datumOd: '',
        datumDo: '',
        adresa: ''
    });
    const [rating, setRating] = useState({});
    const [error, setError] = useState('');
    const [isAdmin, setIsAdmin] = useState(false);
    const userEmail = localStorage.getItem('email');
    const userId = localStorage.getItem('userId');
    const userIme = localStorage.getItem('userIme');
    const userPriimek = localStorage.getItem('userPriimek');
    const [ocene, setOcene] = useState({});

    useEffect(() => {
        const fetchOglasi = async () => {
            try {
                const data = await oglasService.getAllOglasi();
                setOglasi(data);
            } catch (error) {
                setError('Failed to fetch oglasi');
            }
        };

        const checkAdmin = async () => {
            if (userEmail) {
                try {
                    const response = await uporabnikService.isAdmin(userEmail);
                    setIsAdmin(response.data);
                } catch (error) {
                    setError('Failed to check admin status');
                }
            }
        };

        fetchOglasi();
        checkAdmin();
    }, [userEmail]);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFilters({
            ...filters,
            [name]: value
        });
    };

    const handleSearch = async () => {
        try {
            const data = await oglasService.findByFilters(filters);
            setOglasi(data);
        } catch (error) {
            setError('Failed to fetch filtered oglasi');
        }
    };

    const handleRatingChange = (id, value) => {
        setRating({ ...rating, [id]: value });
    };

    const handleApply = async (oglas) => {
        try {
            await oglasService.applyToOglas(oglas);
            alert('Successfully applied to oglas');
        } catch (error) {
            setError('Failed to apply to oglas');
        }
    };

    const handleDelete = async (idOglas) => {
        try {
            await oglasService.deleteOglas(idOglas);
            setOglasi(oglasi.filter(oglas => oglas.idOglas !== idOglas));
            alert('Oglas deleted successfully');
        } catch (error) {
            setError('Failed to delete oglas');
        }
    };

    const fetchOcene = async (oglasId) => {
        try {
            const data = await ocenaService.getOceneByOglas(oglasId);
            setOcene(prevOcene => {
                const updatedOcene = { ...prevOcene, [oglasId]: Array.isArray(data) ? data : [data] };
                return updatedOcene;
            });
        } catch (error) {
            setError('Failed to fetch ocene');
        }
    };

    const handleAddOcena = async (oglasId) => {
        const ocena = {
            oglas: { idOglas: oglasId },
            uporabnik: { idUporabnik: userId, ime: userIme, priimek: userPriimek },
            vrednost: rating[oglasId]
        };
        try {
            const addedOcena = await ocenaService.addOcena(ocena);
            alert('Successfully added ocena');
            setOcene(prevOcene => {
                const updatedOcene = { ...prevOcene };
                if (updatedOcene[oglasId]) {
                    updatedOcene[oglasId].push(addedOcena);
                } else {
                    updatedOcene[oglasId] = [addedOcena];
                }
                return updatedOcene;
            });
        } catch (error) {
            setError('Failed to add ocena');
        }
    };

    return (
        <div className="all-oglas-container">
            <h2>All Oglasi</h2>
            {error && <div className="error">{error}</div>}
            <div className="filter-container">
                <input
                    type="text"
                    name="regija"
                    value={filters.regija}
                    onChange={handleChange}
                    placeholder="Region"
                    className="input-field"
                />
                <input
                    type="date"
                    name="datumOd"
                    value={filters.datumOd}
                    onChange={handleChange}
                    className="input-field"
                />
                <input
                    type="date"
                    name="datumDo"
                    value={filters.datumDo}
                    onChange={handleChange}
                    className="input-field"
                />
                <input
                    type="text"
                    name="adresa"
                    value={filters.adresa}
                    onChange={handleChange}
                    placeholder="Address"
                    className="input-field"
                />
                <button onClick={handleSearch} className="search-button">Search</button>
            </div>
            <div className="oglasi-list">
                {oglasi.map(oglas => (
                    <div key={oglas.idOglas} className="oglas-item">
                        <h3>{oglas.naslov}</h3>
                        <p>{oglas.opis}</p>
                        <p><strong>Address:</strong> {oglas.adresa}</p>
                        <p><strong>From:</strong> {oglas.datumOd}</p>
                        <p><strong>To:</strong> {oglas.datumDo}</p>
                        <div className="rating">
                            <label>
                                Rating:
                                <input
                                    type="number"
                                    value={rating[oglas.idOglas] || ''}
                                    onChange={(e) => handleRatingChange(oglas.idOglas, e.target.value)}
                                    min="1"
                                    max="5"
                                />
                            </label>
                            <button onClick={() => handleAddOcena(oglas.idOglas)}>Add Rating</button>
                        </div>
                        <button className="oglas-button" onClick={() => handleApply(oglas)}>Apply</button>
                        {isAdmin && <button className="oglas-button" onClick={() => handleDelete(oglas.idOglas)}>Delete</button>}
                        <button onClick={() => fetchOcene(oglas.idOglas)}>Show Ratings</button>
                        {(() => {
                            if (Array.isArray(ocene[oglas.idOglas]) && ocene[oglas.idOglas].length > 0) {
                                return (
                                    <div>
                                        <h4>Ratings:</h4>
                                        <ul>
                                            {ocene[oglas.idOglas].map(ocena => (
                                                <li key={ocena.idOcena}>
                                                    {ocena.vrednost} by {ocena.uporabnik.ime} {ocena.uporabnik.priimek}
                                                </li>
                                            ))}
                                        </ul>
                                    </div>
                                );
                            } else {
                                return <p>No ratings available</p>;
                            }
                        })()}
                    </div>
                ))}
            </div>
        </div>
    );
};

export default AllOglas;
