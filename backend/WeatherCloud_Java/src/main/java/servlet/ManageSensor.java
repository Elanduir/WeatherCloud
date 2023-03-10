package servlet;

import com.google.gson.Gson;
import model.SensorData;
import model.SensorID;
import model.SensorOverview;
import service.SQLUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "ManageSensor", urlPatterns = {"/sensorData/*"})
public class ManageSensor extends HttpServlet {


    SQLUtility sql = new SQLUtility();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String body = req.getReader().lines().collect(Collectors.joining());
        SensorData sensorData = new Gson().fromJson(body, SensorData.class);
        sensorData.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:00").format(System.currentTimeMillis()));
        System.out.println(body);
        sql.postSensorData(sensorData);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String requestURL = request.getRequestURI();
        String[] param = requestURL.substring("/WeatherCloudAPI/sensorData/".length()).split("/");
        SensorID id = SensorID.INVALID;
        boolean all = false;
        List<SensorOverview> dataOverview = new ArrayList<>();
        List<SensorData> dataSensor = new ArrayList<>();

        if(param.length == 1){
            if(param[0].equals("true") || param[0].equals("false") || param[0].equals("")){
                all = param[0].equals("true");
            }else{
                id = SensorID.SENSOR01W.getByString(param[0]);
            }
        }else{
            id = SensorID.SENSOR01W.getByString(param[0]);
            all = param[1].equals("true");
        }

        if(id == SensorID.INVALID || id == null){
            dataOverview = sql.getAllSensor(all);
        }else{
            dataSensor = sql.getCurrentSensor(id, all);
        }



        String json = (dataOverview.size() > 0) ? new Gson().toJson(dataOverview) : new Gson().toJson(dataSensor);
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(json);
    }
}
