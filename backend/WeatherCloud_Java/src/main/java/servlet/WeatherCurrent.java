package servlet;

import com.google.gson.Gson;
import model.WeatherData;
import service.SQLUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WeatherCurrent", urlPatterns = {"/weatherCurrent/*"})
public class WeatherCurrent extends HttpServlet {

    SQLUtility sql = new SQLUtility();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {

        List<WeatherData> data = sql.getCurrentData();
        String json = new Gson().toJson(data);
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(json);
    }

}
