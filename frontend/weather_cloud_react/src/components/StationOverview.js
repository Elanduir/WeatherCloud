import WeatherData from "./WeatherData";
import React from "react";
import { useState, useEffect } from "react";
import axios from "axios";

const StationOverview = () => {
  var urlW = "http://192.168.10.30:8080/WeatherCloudAPI/weatherCurrent/wohlen";
  var urlL =
    "http://192.168.10.30:8080/WeatherCloudAPI/weatherCurrent/lenzburg";

  const [dataW, setDataW] = useState([]);
  const [dataL, setDataL] = useState([]);

  useEffect(() => {
    axios
      .get(urlW)
      .then((res) => {
        if (res.data != null) {
          setDataW(res.data);
        }
      })
      .catch((err) => {
        console.error(err);
      });
  }, []);

  if (dataW != null) {
    return (
      <div>
        <WeatherData data={dataW} />
        <WeatherData data={dataL} />
      </div>
    );
  }
};

export default StationOverview;
