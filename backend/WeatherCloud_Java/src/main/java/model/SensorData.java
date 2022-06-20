package model;

public class SensorData {
    String timestamp;
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

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
