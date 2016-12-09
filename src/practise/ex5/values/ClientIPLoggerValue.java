package practise.ex5.values;

import org.apache.catalina.*;
import practise.ex5.core.SimpleWrapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import java.io.IOException;

/**
 * Created by Andrew on 2016/12/9.
 */
public class ClientIPLoggerValue implements Valve,Contained {

    private Container container;

    public ClientIPLoggerValue(Container wrapper) {
        this.container = wrapper;
    }

    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {

        context.invokeNext(request,response);
        System.out.println("Client Remote IP Address:");
        ServletRequest requestRequest = request.getRequest();
        String remoteAddr = requestRequest.getRemoteAddr();
        System.out.println(remoteAddr);
        System.out.println("=========================");

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
