import logo from './logo.svg';
import './App.css';
import React, {useState, useEffect, useCallback} from 'react';
import axios from 'axios';
import {useDropzone} from 'react-dropzone'

const UserProfiles = () => {

  const [UserProfiles, setUserProfiles] = useState([]);

  const fetchUserProfiles = () => {
    axios.get("http://localhost:8080/api/version1/user-profile").then( res => {
    console.log(res);
    setUserProfiles(res.data);
  })
  }
  useEffect(() => {
    fetchUserProfiles();
  }, []);

  return UserProfiles.map((userProfile, index) => {
    return (
    <div key={index}>
      <br />
      <br />
      <h1>{userProfile.userName}</h1>
      <p>{userProfile.userId}</p>
      <Dropzone {...userId}/>
      <br />
    </div>
    )
  })
}
 
function Dropzone({ userId }) {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];
    console.log(file);

    const formData = new FormData();
    formData.append("file", file);

    axios.post('http://localhost:8080/api/version1/user-profile${userId}/image/upload')
  }, [])
  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})


  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the files here ...</p> :
          <p>Drag 'n' drop some files here, or click to select files</p>
      }
    </div>
  )
}

function App() {
  return (
    <div className="App">
      <UserProfiles />
    </div>
  );
}

export default App;
