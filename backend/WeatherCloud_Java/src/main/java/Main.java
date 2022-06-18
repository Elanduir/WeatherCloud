import org.apache.catalina.Globals;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;


public class Main {

    public static void main(String[] args) throws Exception{
        String webappDir = "src/";
        Tomcat tomcat = new Tomcat();

        String port = System.getenv("PORT");
        if(port == null || port.isEmpty()){
            port = "3000";
        }
        tomcat.setPort(Integer.parseInt(port));

        StandardContext context = (StandardContext) tomcat.addWebapp("/", new File(webappDir).getAbsolutePath());

        File addInfo = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", addInfo.getAbsolutePath(), "/"));
        context.setResources(resources);
        context.getServletContext().setAttribute(Globals.ALT_DD_ATTR, "src/main/resources/web.xml");

        tomcat.start();
        tomcat.getServer().await();

    }
}
