import React, { useEffect } from 'react'
import { useNavigate } from 'react-router-dom'
import imagePath from '../images/AD.jpeg'
import Swal from 'sweetalert2';

function Error() {
    const history=useNavigate();
    const style = {
      backgroundImage: `url(${imagePath})`,
      backgroundSize: 'cover',
      backgroundPosition: 'center',
      backgroundRepeat: 'no-repeat',
      height: '100vh',
      width: '100%',
      position: 'relative',
    };


    useEffect(()=>{
      localStorage.removeItem('jwt');
      localStorage.removeItem('role');
      Swal.fire({
        position: "top-end",
        icon: "error",
        title: "Vous n'avez pas la permission d'accéder à cette API",
        showConfirmButton: false,
        timer: 3000
      });
    },[])

    
    const handleLogin = () => { 
    history('/')
    }
  return (
    
    <div className='class' style={style}>
        <span className='span2'><button type="button" className="btn btn-primary espace" onClick={handleLogin}>Clicker ici</button> </span>
    </div>
  )
}

export default Error
