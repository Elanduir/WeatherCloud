import { AgGridReact } from "ag-grid-react";
import "ag-grid-community/dist/styles/ag-grid.css";
import "ag-grid-community/dist/styles/ag-theme-alpine-dark.css";
import React, {
  useState,
  useEffect,
  useMemo,
  useRef,
  useCallback,
} from "react";
import axios from "axios";

const SensorHistory = () => {
  const [rowData, setRowData] = useState();

  const [columnDefs, setColumnDefs] = useState([
    { field: "date", headerName: "Date", width: 150 },
    { field: "s1_temp", headerName: "Fabian Z. Temp", width: 125 },
    { field: "s1_hum", headerName: "Fabian Z. Hum", width: 125 },
    { field: "s2_temp", headerName: "Küche Temp", width: 125 },
    { field: "s2_hum", headerName: "Küche 2 Hum", width: 125 },
  ]);

  const defaultColDef = useMemo(() => ({
    resizable: true,
  }));

  var url = "http://192.168.10.73:3000/sensorData/true";

  const getData = async () => {
    try {
      axios.get(url).then((resp) => {
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
    <div className="sensorGrid">
      <AgGridReact
        rowData={rowData}
        columnDefs={columnDefs}
        defaultColDef={defaultColDef}
        animateRows={true}
        rowSelection="multiple"
        onGridReady={getData}
      />
    </div>
  );
};

export default SensorHistory;
