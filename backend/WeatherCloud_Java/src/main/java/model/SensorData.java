package model;

import java.sql.Date;
import java.sql.Timestamp;

public class SensorData {
    final String timestamp;
    final String id;
    final double temp;
    final double hum;

    public SensorData(String timestamp, String id, double temp, double hum) {
        this.timestamp = timestamp;
        this.id = id;
        this.temp = temp;
        this.hum = hum;
    }

    @Override
    public String toString(){
        return id + "\n" + timestamp + "\n" + temp + "\n" + hum;
    }

    public String getDate() {
        return timestamp;
    }

    public String getId() {
        return id;
    }

    public double getTemp() {
        return temp;
    }

    public double getHum() {
        return hum;
    }
}
