package ex05.pyrmont.startup;

import ex05.pyrmont.core.SimpleLoader;
import ex05.pyrmont.core.SimpleWrapper;
import ex05.pyrmont.valves.ClientIPLoggerValve;
import ex05.pyrmont.valves.HeaderLoggerValve;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.http.HttpConnector;

import java.io.IOException;

/**
 * Created by Andrew on 2016/12/7.
 */
public class MyBootstrap {


    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();

        SimpleWrapper simpleWrapper = new SimpleWrapper();
        simpleWrapper.setServletClass("ModernServlet");
        SimpleLoader simpleLoader = new SimpleLoader();
        simpleWrapper.setLoader(simpleLoader);

        ClientIPLoggerValve ipLoggerValve = new ClientIPLoggerValve();
        HeaderLoggerValve headerLoggerValve = new HeaderLoggerValve();
        simpleWrapper.addValve(ipLoggerValve);
        simpleWrapper.addValve(headerLoggerValve);

        connector.setContainer(simpleWrapper);

        try {
            connector.initialize();
            connector.start();

            // make the application wait until we press a key.
            System.in.read();

        } catch (LifecycleException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
