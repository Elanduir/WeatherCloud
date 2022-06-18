package servlet;

import com.google.gson.Gson;
import model.Location;
import model.WeatherData;
import service.SQLUtility;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WeatherCurrent extends HttpServlet {

    SQLUtility sql = new SQLUtility();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURL = request.getRequestURI();
        String json = "{}";
        Location location = Location.INVALID.getByString(requestURL.substring("/weatherCurrent/".length()));
        if(location != Location.INVALID){
            WeatherData weatherData = sql.getCurrentDataByStation(location);
            json = new Gson().toJson(weatherData);
        }
        response.getOutputStream().print(json);
    }

}
