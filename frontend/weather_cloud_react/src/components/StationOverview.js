import WeatherData from "./WeatherData";
import React from 'react'
import { useState, useEffect } from "react";
import axios from 'axios';

const StationOverview = () => {

    var urlW = "http://localhost:3000/weatherCurrent/wohlen";
    var urlL = "http://localhost:3000/weatherCurrent/lenzburg";

    const[dataW, setDataW] = useState([]);
    const[dataL, setDataL] = useState([]);

    useEffect(() => {
        console.log("hey");
        axios.get(urlW)
            .then(res =>{
                console.log(res);
                if(res.data != null){
                    setDataW(res.data);
                }
            })
            .catch(err =>{
                console.error(err);
            })
        
    },[]);

    if(dataW != null){
        return (
            <div>
                <WeatherData data={dataW}/>
                <WeatherData data={dataL}/>

            </div>
        )
    }
    
}

export default StationOverview