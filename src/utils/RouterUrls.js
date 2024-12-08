import React from 'react'
import { createBrowserRouter } from "react-router-dom";
import PrivateRoutes from './PrivateRoutes';
import HomeUser from '../components/HomeUser';
import HomeAdmin from '../components/HomeAdmin';
import Error from '../components/Error';
import LoginPage from '../components/LoginPage';
import SignupPage from '../components/SignupPage';

const RouterUrls = 
    createBrowserRouter(
    [
        {
            children: [
                {
                    path: "/",
                    element: <LoginPage/>,
                    index: true
                },
                {
                    path: "/signup",
                    element: <SignupPage/>
                },
                {
                    element: <PrivateRoutes/>,
                    path:"*",
                    element:<Error/>
                },

                {
                    element: <PrivateRoutes role="ROLE_USER"/>,
                    children:[
                        {
                            path: "/HomeUser",
                            element: <HomeUser/>
                        },
                    ]
                },
                {
                    element: <PrivateRoutes role="ROLE_ADMIN"/>,
                    children:[
                        {
                            path: "/HomeAdmin",
                            element: <HomeAdmin/>
                        }
                    ]
                },
            ]
        }
    ]
);

export default RouterUrls;
