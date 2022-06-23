import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";

const MobileSensor = () => {
  var url = "http://192.168.10.73:3000/sensorCurrent/";

  const [data, setData] = useState([]);

  const getData = async () => {
    try {
      axios.get(url).then((resp) => {
        setData(resp.data);
      });
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    getData();
    const interval = setInterval(() => {
      getData();
    }, 60000);
    return () => clearInterval(interval);
  }, []);

  var names = {
    "5610_sensor01": "Fabian Zimmer",
    "5610_sensor02": "Küche",
  };

  const sensors = [];
  data.forEach((sensor) => {
    sensors.push(
      <div className="mobileStation">
        <h3>
          {names[sensor["id"]]} - {sensor["timestamp"].substring(11)}
        </h3>
        <table className="mobileSensorTable">
          <tbody>
            <tr>
              <td>Temperature</td>
              <td className="mVal">{sensor["temp"]}°C</td>
            </tr>
            <tr>
              <td>Humidity</td>
              <td className="mVal">{sensor["hum"]}%</td>
            </tr>
            <tr>
              <td>Heat Index</td>
              <td className="mVal">{sensor["hdex"]}°C</td>
            </tr>
          </tbody>
        </table>
      </div>
    );
  });

  return <div className="mobileSensor">{sensors}</div>;
};

export default MobileSensor;
