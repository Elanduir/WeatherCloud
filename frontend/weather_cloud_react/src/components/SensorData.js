import React from 'react'
import { useState, useEffect } from "react";
import axios from 'axios';

const SensorData = () => {

    var url = "http://192.168.10.73:3000/sensorCurrent/" ;

    const[data, setData] = useState([]);

    const getData = async () => {
        try{
            axios.get(url).then((resp) => {
                setData(resp.data);
            })
        }catch(err){
            console.error(err);
        }
    }

    useEffect(() => {
        getData();
        const interval = setInterval(() => {
            getData();
        }, 2000);
        return () => clearInterval(interval);
    },[]);

    const sensors = [];
    data.forEach(element => {
        sensors.push(
            <div className='station'>
                <h2>{element["id"]}</h2>
                <h4>{element["timestamp"]}</h4>
                <table>
                    <tbody>
                        <tr>
                            <td>Temperature</td>
                            <td>{element["temp"]}</td>
                        </tr>
                        <tr>
                            <td>Humidity</td>
                            <td>{element["hum"]}</td>
                        </tr>
                        <tr>
                            <td>Heat Index</td>
                            <td>{element["hdex"]}</td>
                        </tr>
                    </tbody>
                </table>   
            </div>
        )
    });

    return (
        <div className='sensors'>
            {sensors}
        </div>
    )
    
}

export default SensorData