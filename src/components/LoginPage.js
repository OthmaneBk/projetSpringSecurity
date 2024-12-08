import React, {useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { jwtDecode } from 'jwt-decode'
import {
    MDBContainer,
    MDBInput,
} from 'mdb-react-ui-kit';
import { login } from '../Controller';
import imagePath from '../images/SpringSec.jpg';

function LoginPage() {

    const style = {
        backgroundImage: `url(${imagePath})`,
        backgroundSize: 'cover',
        backgroundPosition: 'center',
        backgroundRepeat: 'no-repeat',
      };


    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const history = useNavigate();


    const handleLogin = async () => {
        try {
            if (!username || !password) {
                setError('Please enter both username and password.');
                return;
            }

            const response = await login(username,password)
            setError("")

            const jwt=(response).data['authResponse']['jwt']
            
            const role=jwtDecode(jwt)['authorities'];
            
            localStorage.setItem('jwt',jwt)
            localStorage.setItem('role',role)

            if (role === 'ROLE_USER') {
                history('/HomeUser');
            } else if (role === 'ROLE_ADMIN') {
                history('/HomeAdmin');
            }

        } catch (error) {
            console.error('Login failed:', error.response ? error.response.data : error.message);
        }
    };

    return (
        <div className="d-flex justify-content-center align-items-center vh-100" style={style}>
            <div className="border rounded-lg p-4" style={{ width: '500px', height: 'auto', backgroundColor:'white'}}>
                <MDBContainer className="p-3">
                    <h2 className="mb-4 text-center">Login Page</h2>
                    <MDBInput wrapperClass='mb-4' placeholder='Username' id='Username' value={username} type='text' onChange={(e) => setUsername(e.target.value)} />
                    <MDBInput wrapperClass='mb-4' placeholder='Password' id='password' type='password' value={password} onChange={(e) => setPassword(e.target.value)} />
                    {error && <p className="text-danger">{error}</p>} {/* Render error message if exists */}
                    <button className="mb-4 d-block btn-primary" style={{ height:'50px',width: '100%' }} onClick={handleLogin}>Sign in</button>
                    <div className="text-center">
                        <p>Not a member? <a style={{textDecoration:'None'}}href="/signup" >Register</a></p>
                    </div>
                </MDBContainer>
            </div>
        </div>
    );
}

export default LoginPage;