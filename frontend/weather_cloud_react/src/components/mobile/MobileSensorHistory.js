import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine-dark.css";
import React, { useState, useEffect, useMemo } from "react";
import axios from "axios";

const MobileSensorHistory = () => {
  const [rowData, setRowData] = useState();

  const [columnDefs, setColumnDefs] = useState([
    { field: "s1_temp", headerName: "F Temp", width: 70 },
    { field: "s1_hum", headerName: "F Hum", width: 70 },
    { field: "s2_temp", headerName: "K Temp", width: 70 },
    { field: "s2_hum", headerName: "K Hum", width: 70 },
    { field: "date", headerName: "Date", width: 130 },
  ]);

  const defaultColDef = useMemo(() => ({
    sortable: true,
  }));

  var url = "http://192.168.10.73:3000/sensorData/true";

  const getData = async () => {
    try {
      axios.get(url).then((resp) => {
        console.log(resp);
        setRowData(resp.data);
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
    <div className="mobileSensorGrid">
      <AgGridReact
        rowData={rowData}
        columnDefs={columnDefs}
        defaultColDef={defaultColDef}
        animateRows={true}
        rowSelection="multiple"
      />
    </div>
  );
};

export default MobileSensorHistory;
