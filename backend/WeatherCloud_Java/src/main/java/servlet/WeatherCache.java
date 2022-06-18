package servlet;

import com.google.gson.Gson;
import model.Location;
import model.WeatherData;
import service.SQLUtility;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class WeatherCache extends HttpServlet {
    SQLUtility sql = new SQLUtility();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURL = request.getRequestURI();
        String json = "{}";
        Location location = Location.INVALID.getByString(requestURL.substring("/weatherCache/".length()));
        if(location != Location.INVALID){
            List<WeatherData> weatherCache = sql.getLocationCache(location);
            json = new Gson().toJson(weatherCache);
        }
        response.getOutputStream().print(json);
    }
}
