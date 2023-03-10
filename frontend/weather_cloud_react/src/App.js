import "./App.css";
import "./MobileApp.css";
import SensorData from "./components/SensorData";
import WeatherData from "./components/WeatherData";
import ParticlesBackground from "./components/ParticlesBackground";
import SensorHistory from "./components/SensorHistory";
import useCheckMobileScreen from "./services/CheckMobileScreen";
import MobileSensor from "./components/mobile/MobileSensor";
import MobileSensorHistory from "./components/mobile/MobileSensorHistory";
import SensorChart from "./components/SensorChart";

function App() {
  var width = useCheckMobileScreen();
  var isMobile = width < 900;
  var isUnacceptable = width < 300;

  if (!isMobile) {
    return (
      <div className="master">
        <div className="title">
          <h1>Weather Cloud</h1>
        </div>
        <div className="content">
          <div className="container">
            <div className="sensorDiv">
              <SensorData />
              <div className="table">
                <SensorHistory />
              </div>
            </div>
            <div>
              <WeatherData />
            </div>
          </div>
        </div>
        <ParticlesBackground className="particles" />
      </div>
    );
  } else if (isMobile && !isUnacceptable) {
    return (
      <div className="master">
        <div className="mobileTitle">
          <h1>Weather Cloud</h1>
        </div>
        <div className="mobileContent">
          <MobileSensor />
          <MobileSensorHistory />
        </div>
        <ParticlesBackground className="particles" />
      </div>
    );
  } else {
    return (
      <div>
        <h1>Get a real device mate</h1>
      </div>
    );
  }
}

export default App;
