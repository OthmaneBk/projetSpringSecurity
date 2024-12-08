import './css/App.css';
import React from 'react';
import RouterUrls from './utils/RouterUrls';
import { RouterProvider } from 'react-router-dom';


function App() {
  return (
      <>
          <RouterProvider router={RouterUrls}/>
      </>
  );
}

export default App;
