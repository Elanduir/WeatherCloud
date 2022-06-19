import com.google.gson.Gson;
import model.SensorData;

import java.sql.Date;
import java.sql.Timestamp;

public class Run {
    public static void main(String[] args) {
        SensorData sensorData = new SensorData(new Timestamp(2022, 04, 01, 10, 20, 0, 0), "sensor_01", 10.0, 80.0);
        String json = new Gson().toJson(sensorData);
        System.out.println(json);
    }
}
