import React from 'react'
import { useState, useEffect } from "react";
import axios from 'axios';

const WeatherData = () => {

    var url = "http://localhost:3000/weatherCurrent/" ;

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

    const stations = [];
    data.forEach(element => {
        stations.push(
            <div className='station'>
                <h2>{element["loc"]}</h2>
                <h4>{element["date"]}</h4>
                <table>
                    <tbody>
                        <tr>
                            <td>Temperature</td>
                            <td>{element["temp"]}</td>
                        </tr>
                        <tr>
                            <td>Humidity</td>
                            <td>{element["humidity"]}</td>
                        </tr>
                        <tr>
                            <td>Pressure</td>
                            <td>{element["pressure"]}</td>
                        </tr>
                    </tbody>
                </table>   
            </div>
        )
    });

    return (
        <div className='stations'>
            {stations}
        </div>
    )
    
}

export default WeatherData