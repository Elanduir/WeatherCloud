import './App.css';
import StationOverview from "./components/StationOverview";
import WeatherData from './components/WeatherData';

function App() {
  return (
    <div>
      <h1>Weather Data</h1>
      <div className="container">
        <WeatherData/>
      </div>
    </div>
    
  );
}

export default App;
