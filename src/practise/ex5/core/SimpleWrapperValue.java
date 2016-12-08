package practise.ex5.core;

import org.apache.catalina.*;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by Andrew on 2016/12/8.
 */
public class SimpleWrapperValue implements Valve,Contained {

    private Container simpleWrapper;

    public String getInfo() {
        return null;
    }

    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {


        Wrapper wrapper = (SimpleWrapper)getContainer();
        Servlet servlet =  wrapper.allocate();
        ServletRequest sreq = request.getRequest();
        ServletResponse srsp = response.getResponse();

        servlet.service(sreq,srsp);

    }

    public Container getContainer() {
        return simpleWrapper;
    }

    public void setContainer(Container container) {
        this.simpleWrapper = container;
    }
}
