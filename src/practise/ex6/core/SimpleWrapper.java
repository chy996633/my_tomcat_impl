package practise.ex6.core;

import ex05.pyrmont.core.SimplePipeline;
import org.apache.catalina.*;
import org.apache.catalina.logger.SystemOutLogger;
import org.apache.catalina.util.LifecycleSupport;
import practise.ex5.core.SimpleWrapperValue;

import javax.naming.directory.DirContext;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * Created by Andrew on 2016/12/8.
 */
public class SimpleWrapper implements Wrapper,Pipeline,Lifecycle{
    private Servlet instance = null;
    private String servletClass;
    private Loader loader;
    private String name;
    private SimplePipeline pipeline = new SimplePipeline(this);
    private LifecycleSupport lifecycleSupport = new LifecycleSupport(this);
    private Container parent;

    public SimpleWrapper(){
        pipeline.setBasic(new SimpleWrapperValue());
    }

    public Valve getBasic() {
        return pipeline.getBasic();
    }

    public void setBasic(Valve valve) {
        pipeline.setBasic(valve);
    }

    public synchronized void addValve(Valve valve){
        pipeline.addValve(valve);
    }

    public Valve[] getValves() {
        return pipeline.getValves();
    }

    public String getInfo() {
        return null;
    }

    public Loader getLoader() {
        return loader;
    }

    public void setLoader(Loader loader) {
        this.loader = loader;
    }

    public Logger getLogger() {
        return new SystemOutLogger();
    }

    public void setLogger(Logger logger) {

    }

    public Manager getManager() {
        return null;
    }

    public void setManager(Manager manager) {

    }

    public Cluster getCluster() {
        return null;
    }

    public void setCluster(Cluster cluster) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Container getParent() {
        return parent;
    }

    public void setParent(Container container) {
        this.parent = container;
        container.addChild(this);
    }

    public ClassLoader getParentClassLoader() {
        return null;
    }

    public void setParentClassLoader(ClassLoader parent) {

    }

    public Realm getRealm() {
        return null;
    }

    public void setRealm(Realm realm) {

    }

    public DirContext getResources() {
        return null;
    }

    public void setResources(DirContext resources) {

    }

    public void addChild(Container child) {

    }

    public void addContainerListener(ContainerListener listener) {

    }

    public void addMapper(Mapper mapper) {

    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }

    public Container findChild(String name) {
        return null;
    }

    public Container[] findChildren() {
        return new Container[0];
    }

    public ContainerListener[] findContainerListeners() {
        return new ContainerListener[0];
    }

    public Mapper findMapper(String protocol) {
        return null;
    }

    public Mapper[] findMappers() {
        return new Mapper[0];
    }

    public void invoke(Request request, Response response) throws IOException, ServletException {
        pipeline.invoke(request,response);
    }

    public void removeValve(Valve valve) {

    }

    public Container map(Request request, boolean update) {
        return null;
    }

    public void removeChild(Container child) {

    }

    public void removeContainerListener(ContainerListener listener) {

    }

    public void removeMapper(Mapper mapper) {

    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {

    }

    public long getAvailable() {
        return 0;
    }

    public void setAvailable(long available) {

    }

    public String getJspFile() {
        return null;
    }

    public void setJspFile(String jspFile) {

    }

    public int getLoadOnStartup() {
        return 0;
    }

    public void setLoadOnStartup(int value) {

    }

    public String getRunAs() {
        return null;
    }

    public void setRunAs(String runAs) {

    }

    public String getServletClass() {
        return null;
    }

    public void setServletClass(String servletClass) {
        this.servletClass = servletClass;
    }

    public boolean isUnavailable() {
        return false;
    }

    public void addInitParameter(String name, String value) {

    }

    public void addInstanceListener(InstanceListener listener) {

    }

    public void addSecurityReference(String name, String link) {

    }

    public Servlet allocate() throws ServletException {
        if (instance == null){
            instance = loadServlet();
        }
        return instance;
    }

    private Servlet loadServlet() {

        if (instance!=null)
            return instance;

        Loader loader =  getParent().getLoader();
        ClassLoader classLoader = loader.getClassLoader();

        Class aClass = null;
        Servlet servlet = null;
        try {
            aClass = classLoader.loadClass(servletClass);
            servlet = (Servlet)aClass.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return servlet;
    }

    public void deallocate(Servlet servlet) throws ServletException {

    }

    public String findInitParameter(String name) {
        return null;
    }

    public String[] findInitParameters() {
        return new String[0];
    }

    public String findSecurityReference(String name) {
        return null;
    }

    public String[] findSecurityReferences() {
        return new String[0];
    }

    public void load() throws ServletException {

    }

    public void removeInitParameter(String name) {

    }

    public void removeInstanceListener(InstanceListener listener) {

    }

    public void removeSecurityReference(String name) {

    }

    public void unavailable(UnavailableException unavailable) {

    }

    public void unload() throws ServletException {

    }

    public void addLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.addLifecycleListener(listener);
    }

    public LifecycleListener[] findLifecycleListeners() {
        return lifecycleSupport.findLifecycleListeners();
    }

    public void removeLifecycleListener(LifecycleListener listener) {
        lifecycleSupport.removeLifecycleListener(listener);
    }

    public void start() throws LifecycleException {
        lifecycleSupport.fireLifecycleEvent(Lifecycle.BEFORE_START_EVENT,null);

        Loader loader = getLoader();
        if (loader instanceof Lifecycle){
            ((Lifecycle)loader).start();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.START_EVENT,null);

        if (pipeline instanceof Lifecycle){
            ((Lifecycle)pipeline).start();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.AFTER_START_EVENT,null);


    }

    public void stop() throws LifecycleException {
        lifecycleSupport.fireLifecycleEvent(Lifecycle.BEFORE_STOP_EVENT,null);

        Loader loader = getLoader();
        if (loader instanceof Lifecycle){
            ((Lifecycle)loader).stop();
        }

        lifecycleSupport.fireLifecycleEvent(Lifecycle.STOP_EVENT,null);

        if (pipeline instanceof Lifecycle){
            ((Lifecycle)pipeline).start();
        }


        lifecycleSupport.fireLifecycleEvent(Lifecycle.AFTER_STOP_EVENT,null);
    }
}
