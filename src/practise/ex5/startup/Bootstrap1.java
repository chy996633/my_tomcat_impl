package practise.ex5.startup;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.Loader;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import practise.ex5.core.SimpleLoader;
import practise.ex5.core.SimpleWrapper;

import java.io.IOException;

/**
 * 使用wrapper作为container
 * Created by Andrew on 2016/12/8.
 */
public class Bootstrap1 {


    public static void main(String[] args) {
        HttpConnector httpConnector = new HttpConnector();
        Wrapper wrapper = new SimpleWrapper();
        wrapper.setServletClass("ModernServlet");

        Loader simpleLoader = new SimpleLoader();
        wrapper.setLoader(simpleLoader);

        simpleLoader.setContainer(wrapper);

        httpConnector.setContainer(wrapper);

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
