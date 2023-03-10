import axios from "axios";
import { useState, useEffect } from "react";
import { Line } from "react-chartjs-2";

const SensorChart = () => {
  var url = "http://192.168.10.30:8080/WeatherCloudAPI/sensorData/true";

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

  return (
    <div className="chart">
      <Line
        data={data}
        options={{
          plugins: {
            title: {
              display: true,
              text: "Cryptocurrency prices",
            },
            legend: {
              display: true,
              position: "bottom",
            },
          },
        }}
      />
    </div>
  );
};

export default SensorChart;
