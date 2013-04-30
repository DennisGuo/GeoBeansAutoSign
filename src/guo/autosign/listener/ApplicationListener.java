package guo.autosign.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationListener implements ServletContextListener{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("------ GeoBeans 自动签到系统 启动中-------");
		
		
	}
}
