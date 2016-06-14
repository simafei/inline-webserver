package cn.simafei.action;

import cn.simafei.util.JettyServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by fengpj on 2016/6/12.
 * @author fengpj(simafei)
 * 启动器
 */
public class Bootstrap {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Bootstrap.class);
	private static ApplicationContext applicationContext;
	private final static String[] configLocations = { "spring-ctx-application.xml" };

	public static void main(String[] args) {
		initializeSpring();
		initializeJettyServer();
	}

	/**
	 * <pre>
	 * 初始化spring
	 * </pre>
	 */
	private static void initializeSpring() {
		applicationContext = new ClassPathXmlApplicationContext(configLocations);
	}

	/**
	 * <pre>
	 * 运行 jetty server
	 * </pre>
	 */
	private static void initializeJettyServer() {
		try {
			assert applicationContext != null;
			JettyServer server = applicationContext.getBean("jettyServer",
					JettyServer.class);
			LOGGER.info("start to run jetty server.");
			server.runJettyServer();
		} catch (Exception e) {
			LOGGER.error("Error:" + e.getMessage(), e);
		}
	}

}
