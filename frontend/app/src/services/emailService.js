const API_URL = 'http://localhost:8080/api/email';

const sendEmail = async (email) => {
    const response = await fetch(API_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(email)
    });
    if (!response.ok) {
        throw new Error('Failed to send email');
    }
    return response.json();
};

export default { sendEmail };
