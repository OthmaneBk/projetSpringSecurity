import {React }from 'react';
import { Navigate, Outlet } from 'react-router-dom';



const PrivateRoutes = ({ role}) => {
    console.log(localStorage.getItem('jwt'))
    let token = localStorage.getItem('jwt');
    let roleOfUser = localStorage.getItem('role');
    
    return(
        token && roleOfUser===role? <Outlet/> : <Navigate to="*"/>
    ); 
};

export default PrivateRoutes;
