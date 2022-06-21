package model;




public class WeatherData {

    final Location loc;
    final double temp;
    final double humidity;
    final double pressure;

    final double hdex;

    final String date;

    public WeatherData(Location loc, String date, double temp, double humidity, double pressure, double hdex){
        this.loc = loc;
        this.date = date;
        this.temp = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        this.hdex = hdex;
    }

    public Location getLoc() {
        return loc;
    }

    public double getTemp() {
        return temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public String getDate() {
        return date;
    }

    public double getHdex() {
        return hdex;
    }
}
