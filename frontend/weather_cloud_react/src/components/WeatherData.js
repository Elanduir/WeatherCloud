import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";

const WeatherData = () => {
  var url = "http://192.168.10.30:8080/WeatherCloudAPI/weatherCurrent/";

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

  console.log(data);

  const stations = [];
  data.forEach((element) => {
    stations.push(
      <div className="station">
        <h2>{element["loc"]}</h2>
        <h4>{element["date"]}</h4>
        <table>
          <tbody>
            <tr>
              <td>Temperature</td>
              <td className="val">{element["temp"]}°C</td>
            </tr>
            <tr>
              <td>Humidity</td>
              <td className="val">{element["humidity"]}%</td>
            </tr>
            <tr>
              <td>Pressure</td>
              <td className="val">{element["pressure"]}hPa</td>
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

  return <div className="stations">{stations}</div>;
};

export default WeatherData;
