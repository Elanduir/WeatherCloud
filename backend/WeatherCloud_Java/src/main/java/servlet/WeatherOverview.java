package servlet;

import com.google.gson.Gson;
import model.OverviewData;
import service.SQLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "WeatherOverview", urlPatterns = {"/weatherOverview/*"})
public class WeatherOverview extends HttpServlet {
    SQLUtility sql = new SQLUtility();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OverviewData> overview = sql.getStationOverview();
        String json = new Gson().toJson(overview);
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(json);
    }
}
