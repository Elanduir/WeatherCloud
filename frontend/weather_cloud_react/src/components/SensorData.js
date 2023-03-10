import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";

const SensorData = () => {
  var url = "http://192.168.10.30:8080/WeatherCloudAPI/sensorCurrent/";

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
  data.forEach((element) => {
    sensors.push(
      <div className="station">
        <h2>{names[element["id"]]}</h2>
        <h4>{element["timestamp"].substring(11)}</h4>
        <table>
          <tbody>
            <tr>
              <td>Temperature</td>
              <td className="val">{element["temp"]}°C</td>
            </tr>
            <tr>
              <td>Humidity</td>
              <td className="val">{element["hum"]}%</td>
            </tr>
            <tr>
              <td>Heat Index</td>
              <td className="val">{element["hdex"]}°C</td>
            </tr>
          </tbody>
        </table>
      </div>
    );
  });

  return <div className="sensors">{sensors}</div>;
};

export default SensorData;
