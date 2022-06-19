import { AgGridReact } from 'ag-grid-react';
import 'ag-grid-community/dist/styles/ag-grid.css';
import React, { useState, useEffect, useMemo} from 'react';
import axios from 'axios';


const SensorHistory = () => {
  const [rowData, setRowData] = useState();

  const [columnDefs, setColumnDefs] =useState([
    {field: 'date', headerName: "Date"},
    {field: 's1_temp', headerName: "Sensor 1 Temp"},
    {field: 's1_hum', headerName: "Sensor 1 Hum"},
    {field: 's2_temp', headerName: "Sensor 2 Temp"},
    {field: 's2_hum', headerName: "Sensor 1 Hum"}
  ]);

  const defaultColDef = useMemo( ()=> ({
    sortable: true
  }));

  var url = "http://192.168.10.119:3000/sensorData/true" ;

  const getData = async () => {
      try{
          axios.get(url).then((resp) => {
            setRowData(resp.data);
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

  console.log(rowData);

  return (
    <div className='history'>
      <div className="ag-theme-alpine" style={{width: "100%", height: "200px"}}>

      <AgGridReact
          rowData={rowData}
          columnDefs={columnDefs}
          defaultColDef={defaultColDef}
          animateRows={true}
          rowSelection='multiple'
          />
      </div>
    </div>
  )
}

export default SensorHistory