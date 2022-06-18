package model;

public class OverviewData {
    String date;

    Double tempWohlen;
    Double humidityWohlen;
    Double pressureWohlen;

    Double tempLenzburg;
    Double humidityLenzburg;
    Double pressureLenzburg;

    public OverviewData(WeatherData wohlen, WeatherData Lenzburg){
        this.date = wohlen.date;

        this.tempWohlen = wohlen.temp;
        this.humidityWohlen = wohlen.humidity;
        this.pressureWohlen = wohlen.pressure;

        this.tempLenzburg = Lenzburg.temp;
        this.humidityLenzburg = Lenzburg.humidity;
        this.pressureLenzburg = Lenzburg.pressure;
    }


    public String getDate() {
        return date;
    }

    public Double getTempWohlen() {
        return tempWohlen;
    }

    public Double getHumidityWohlen() {
        return humidityWohlen;
    }

    public Double getPressureWohlen() {
        return pressureWohlen;
    }

    public Double getTempLenzburg() {
        return tempLenzburg;
    }

    public Double getHumidityLenzburg() {
        return humidityLenzburg;
    }

    public Double getPressureLenzburg() {
        return pressureLenzburg;
    }
}
