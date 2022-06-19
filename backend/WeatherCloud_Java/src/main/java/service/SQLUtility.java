package service;



import model.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SQLUtility {
    Connection db;
    Statement stmt;
    final String PATTERN = "MM.dd.yyyy HH:mm";
    public SQLUtility(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.db = DriverManager.getConnection("jdbc:mysql://192.168.10.73:3306/db_weather", "root", "wasser1");
            stmt = db.createStatement();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public WeatherData getCurrentDataByStation(Location loc){
        if(loc == Location.INVALID) throw new IllegalArgumentException();
        String statement = "SELECT * FROM " + loc.toString() + "_cache order by date_created desc limit 1";
        try{
            ResultSet rs = stmt.executeQuery(statement);
            rs.next();
            return new WeatherData(loc, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4));
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<OverviewData> getStationOverview(){
        List<OverviewData> overview = new ArrayList<>();
        String statement = "SELECT * FROM v_weatherStationOverview";
        WeatherData wW;
        WeatherData wL;
        try{
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()){
                wW = new WeatherData(Location.WOHLEN, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4));
                wL = new WeatherData(Location.LENZBURG, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7));
                OverviewData o = new OverviewData(wW, wL);
                overview.add(o);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return overview;
    }

    public List<WeatherData> getLocationCache(Location loc){
        String statement = "SELECT * FROM " + loc.toString() + "_cache order by date_created desc limit 100";
        List<WeatherData> locationCache = new ArrayList<>();
        try{
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()){
                locationCache.add(new WeatherData(loc, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return locationCache;
    }

    public List<WeatherData> getCurrentData(){
        List<WeatherData> data = new ArrayList<>();
        for(Location loc : Location.values()){
            if(loc != Location.INVALID){
                String statement = "SELECT * FROM " + loc.toString() + "_cache order by date_created desc limit 1";
                try{
                    ResultSet rs = stmt.executeQuery(statement);
                    while(rs.next()){
                        data.add(new WeatherData(loc, new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4)));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
        return data;
    }

    public List<SensorData> getCurrentSensor(SensorID id, Boolean all){
        String statement = "Select * from " + id.toString() + " order by date_created desc";
        statement += (all) ? ";" : " limit 1;";
        List<SensorData> data = new ArrayList<>();
        try{
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()){
                data.add(new SensorData(new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return data;

    }

    public List<SensorData> getAllSensorCurrent(){
        List<SensorData> data = new ArrayList<>();
        for(SensorID sid : SensorID.values()){
            if(sid != SensorID.INVALID){
                String statement = "Select * from " + sid.toString() + " order by date_created desc limit 1;";
                try{
                    ResultSet rs = stmt.executeQuery(statement);
                    while(rs.next()){
                        data.add(new SensorData(new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getString(2), rs.getDouble(3), rs.getDouble(4)));
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    public List<SensorOverview> getAllSensor(Boolean all){
        List<SensorOverview> data = new ArrayList<>();

        String statement = "select * from v_sensorOverview order by Date desc";
        statement += (all) ? ";" : " limit 1;";
        try{
            ResultSet rs = stmt.executeQuery(statement);
            while(rs.next()){
                data.add(new SensorOverview(new SimpleDateFormat(PATTERN).format(rs.getTimestamp(1)), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5)));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return data;
    }

    public void postSensorData(SensorData sensorData){
        String statement = "INSERT INTO " + sensorData.getId() +
                "(date_created, sensor_id, temperature, humidity) VALUES(?, ?, ?, ?);";
        try{
            PreparedStatement pstmt = db.prepareStatement(statement);
            pstmt.setTimestamp(1, Timestamp.valueOf(sensorData.getDate()));
            pstmt.setString(2, sensorData.getId());
            pstmt.setDouble(3, sensorData.getTemp());
            pstmt.setDouble(4, sensorData.getHum());
            pstmt.execute();
            System.out.println("Record inserted...");

        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void close(){
        try{
            db.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
