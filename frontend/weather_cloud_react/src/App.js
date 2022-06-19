import './App.css';
import SensorData from './components/SensorData';
import StationOverview from "./components/StationOverview";
import WeatherData from './components/WeatherData';

function App() {
  return (
    <div>
      <h1>Weather Data</h1>
      <div className="container">
        <div>
          <SensorData/>
        </div>
        <div>
          <WeatherData/>
        </div>
      </div>
      
    </div>
    
  );
}

export default App;
