package servlet;

import com.google.gson.Gson;
import model.Location;
import model.SensorData;
import model.SensorID;
import model.WeatherData;
import service.SQLUtility;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ManageSensor extends HttpServlet {


    SQLUtility sql = new SQLUtility();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining());
        SensorData sensorData = new Gson().fromJson(body, SensorData.class);
        sql.postSensorData(sensorData);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String requestURL = request.getRequestURI();
        String[] param = requestURL.substring("/sensorData/".length()).split("/");
        SensorID id = SensorID.sensor01W.getByString(param[0]);
        Boolean all = param.length > 1 && param[1].equals("true");
        List<SensorData> data = sql.getCurrentSensor(id, all);
        String json = new Gson().toJson(data);
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(json);
    }
}
