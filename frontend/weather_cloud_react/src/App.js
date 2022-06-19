import './App.css';
import SensorData from './components/SensorData';
import WeatherData from './components/WeatherData';
import ParticlesBackground from './components/ParticlesBackground';
import SensorHistory from './components/SensorHistory';
import React, { useState, useEffect} from 'react';
import useCheckMobileScreen from './services/CheckMobileScreen';

function App() {
  var width = useCheckMobileScreen();
  var isMobile = width< 900;


  if(!isMobile){
    return (
      <div className='master'>
        <div className='title'>
          <h1>Weather Cloud</h1>
        </div>

        <div className='content'>
          
          <div className="container">
            <div className='sensorDiv'>
              <SensorData/>
              <div className='table'>
                <SensorHistory/>
              </div>
            </div>
            <div>
              <WeatherData/>
            </div>
          </div>
        </div>
        <ParticlesBackground className="particles"/>
      </div>
      
    );
  }else{
    return(
      <div>
        <h1>Unsupported Screen Size: {width}</h1>
      </div>
    )
  }

  
}

export default App;
