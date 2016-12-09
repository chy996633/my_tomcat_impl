package practise.ex5.core;

import org.apache.catalina.Container;
import org.apache.catalina.Mapper;
import org.apache.catalina.Request;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Andrew on 2016/12/9.
 */
public class SimpleContextMapper implements Mapper {

    private Container container;
    private String protocol;
    private HashMap mapper = new HashMap();


    public void addMap(String url,String wrapperName){
      mapper.put(url,wrapperName) ;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        container.addMapper((Mapper)this);
        this.container = container;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Container map(Request request, boolean update) {

        ServletRequest servletRequest = request.getRequest();
        if (servletRequest instanceof HttpServletRequest){
            HttpServletRequest hreq = (HttpServletRequest)servletRequest;
            String requestURI = hreq.getRequestURI();
            String wrapName = (String)mapper.get(requestURI);
            Container wrapper = container.findChild(wrapName);
            return wrapper;
        }

        return null;
    }
}
