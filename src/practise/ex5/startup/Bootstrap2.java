package practise.ex5.startup;

import ex05.pyrmont.core.SimpleLoader;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.http.HttpConnector;
import practise.ex5.core.SimpleContext;
import practise.ex5.core.SimpleContextMapper;
import practise.ex5.core.SimpleWrapper;

import java.io.IOException;

/**
 * 使用SimpleContext作为外层container
 * simpleMapper来映射不同的simpleWrapper，从而加载不同的Servlet
 * Created by Andrew on 2016/12/9.
 */
public class Bootstrap2  {


    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        SimpleContext simpleContext = new SimpleContext();
        simpleContext.setName("Andrew simpleContext");

        SimpleContextMapper mapper = new SimpleContextMapper();
        mapper.setContainer(simpleContext);
        mapper.setProtocol("HTTP");
        mapper.addMap("/ModernServlet","Modern");
        mapper.addMap("/M","Modern");
        mapper.addMap("/PrimitiveServlet","Primitive");
        mapper.addMap("/P","Primitive");
        mapper.addMap("/S","Session");
        simpleContext.addMapper(mapper);

        SimpleWrapper wrapper1 = new SimpleWrapper();
        wrapper1.setServletClass("ModernServlet");
        wrapper1.setName("Modern");
        wrapper1.setParent(simpleContext);

        SimpleWrapper wrapper2 = new SimpleWrapper();
        wrapper2.setServletClass("PrimitiveServlet");
        wrapper2.setName("Primitive");
        wrapper2.setParent(simpleContext);

        SimpleWrapper wrapper3 = new SimpleWrapper();
        wrapper3.setServletClass("SessionServlet");
        wrapper3.setName("Session");
        wrapper3.setParent(simpleContext);


        SimpleLoader simpleLoader = new SimpleLoader();
        simpleContext.setLoader(simpleLoader);

        httpConnector.setContainer(simpleContext);
        try {

            httpConnector.initialize();
            httpConnector.start();

            System.in.read();

        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
