package practise.ex5.values;

import org.apache.catalina.*;
import practise.ex5.core.SimpleWrapper;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * Created by Andrew on 2016/12/9.
 */
public class HeaderLoggerValve implements Contained,Valve {

    private Container container;

    public HeaderLoggerValve(Container wrapper) {
        this.container = wrapper;
    }

    public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {

        context.invokeNext(request,response);
        System.out.println("Header Logger Valve:");
        ServletRequest request1 = request.getRequest();
        String remoteHost = request1.getRemoteHost();
        String serverName = request1.getServerName();
        int serverPort = request1.getServerPort();
        Map parameterMap = request1.getParameterMap();
        System.out.println("remoteHost:"+remoteHost);
        System.out.println("serverName:"+serverName);
        System.out.println("serverPort:"+serverPort);
        System.out.println("parameterMap:"+parameterMap);
        System.out.println("serverName:"+getContainer().getName());
        if (request1 instanceof HttpServletRequest){
            HttpServletRequest hreq = (HttpServletRequest)request1;
            Enumeration headerNames = hreq.getHeaderNames();
            while (headerNames.hasMoreElements()){
                String headerName = headerNames.nextElement().toString();
                String header = hreq.getHeader(headerName);
                System.out.println(headerName+" : "+header);
            }

        }

        System.out.println("=====================");

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
