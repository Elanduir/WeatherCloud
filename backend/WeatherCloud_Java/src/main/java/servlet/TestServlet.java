package servlet;

import com.google.gson.Gson;
import model.SensorData;
import model.SensorOverview;
import service.SQLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Test", urlPatterns = {"/MyServlet"})
public class TestServlet extends HttpServlet {
    SQLUtility sql = new SQLUtility();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SensorData> sensorOverview = sql.getAllSensorCurrent();
        String json = new Gson().toJson(sensorOverview);
        resp.getOutputStream().print(json);
    }
}
