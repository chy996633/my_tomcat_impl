package practise.ex6.core;

import org.apache.catalina.*;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by Andrew on 2016/12/9.
 */
public class SimpleContextValve implements Contained,Valve {

    private Container container;

    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
        Mapper mapper = container.findMapper("HTTP");
        Wrapper wrapper = (Wrapper) mapper.map(request,false);
        Servlet servlet = wrapper.allocate();
        servlet.service(request.getRequest(),response.getResponse());
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public String getInfo() {
        return null;
    }



}
