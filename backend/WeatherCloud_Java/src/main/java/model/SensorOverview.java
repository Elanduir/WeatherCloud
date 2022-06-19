package model;

import java.sql.Timestamp;

public class SensorOverview {
    final String date;
    final double s1_temp;
    final double s1_hum;
    final double s2_temp;
    final double s2_hum;

    public SensorOverview(String date, double s1_temp, double s1_hum, double s2_temp, double s2_hum) {
        this.date = date;
        this.s1_temp = s1_temp;
        this.s1_hum = s1_hum;
        this.s2_temp = s2_temp;
        this.s2_hum = s2_hum;
    }

    public String getDate() {
        return date;
    }

    public double getS1_temp() {
        return s1_temp;
    }

    public double getS1_hum() {
        return s1_hum;
    }

    public double getS2_temp() {
        return s2_temp;
    }

    public double getS2_hum() {
        return s2_hum;
    }
}
