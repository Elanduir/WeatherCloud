package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultResponse extends HttpServlet {

    String invalidRequest = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <style>\n" +
            "        * {\n" +
            "            font-family: Arial, Helvetica, sans-serif;\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<div style=\"display: flex;justify-content: center; align-items: center;height: 100vh;\"\\>\n" +
            "    <h1>Invalid Request</h1>\n" +
            "</div>\n" +
            "</body>\n" +
            "</html>";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(invalidRequest);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("AccesssensorData-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(invalidRequest);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(invalidRequest);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin","*");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getOutputStream().print(invalidRequest);
    }


}
