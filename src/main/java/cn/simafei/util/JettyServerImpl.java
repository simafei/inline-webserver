package cn.simafei.util;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;

/**
 * Created by fengpj on 2016/6/12.
 * @author fengpj(simafei)
 * 集成SpringMVC的内嵌WebServer
 */
public class JettyServerImpl implements JettyServer {
	private Server server;
	private String path;
	private Servlet servlet;

	public void setPath(String path) {
		this.path = path;
	}

	public void setServlet(Servlet servlet) {
		this.servlet = servlet;
	}

	private ServletContextHandler servletContextHandler;

	public void runJettyServer() throws Exception {
		//可以添加多个Servlet，这里只添加了SpringMVC的
		servletContextHandler.addServlet(new ServletHolder(servlet), path);

		server.start();
		server.join();
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public void setServletContextHandler(
			ServletContextHandler servletContextHandler) {
		this.servletContextHandler = servletContextHandler;
	}
}
