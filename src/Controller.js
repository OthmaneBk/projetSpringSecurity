import axios from 'axios';

export const login=(username,pwd) => {
    return axios.post('http://localhost:8080/test/Login', { username, pwd });
}

export const signup=(nomutilisateur,motdepasse,roleuti) =>{
    return axios.post('http://localhost:8080/test/SignUp', { 
        nomutilisateur,
        motdepasse,
        roleuti
    });
}