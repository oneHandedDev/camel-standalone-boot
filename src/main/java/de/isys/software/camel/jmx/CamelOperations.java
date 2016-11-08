package de.isys.software.camel.jmx;

import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by stefanhaupt on 02.08.2016.
 */
@Component
@ManagedResource(description = "Camel Interfaces for Operations actions")
public class CamelOperations implements ApplicationContextAware {

	private Log log = LogFactory.getLog(CamelOperations.class);

	@Autowired
	private CamelContext camelContext;

	@Autowired
	private ApplicationContext applicationContext;

	private List<Route> getRoutes() {
		return camelContext.getRoutes();
	}

	@ManagedAttribute
	public String showRoutes() {
		StringBuilder b = new StringBuilder();
		getRoutes().forEach(route -> b.append(route.toString()));
		return b.toString();
	}

	@ManagedOperation(description = "stops all camel routes, and thus sends a shutdownhook to the application")
	public void stop() {
		getRoutes().forEach(route -> doRouteAction(ActionEnum.STOP, route));
	}

	@ManagedOperation(description = "suspends all camel routes")
	public void suspend() {
		getRoutes().forEach(route -> doRouteAction(ActionEnum.SUSPEND, route));
	}

	@ManagedOperation(description = "resumes all suspended camel routes")
	public void resume() {
		getRoutes().forEach(route -> doRouteAction(ActionEnum.RESUME, route));
	}

	private void doRouteAction(ActionEnum action, Route route) {
		try {
			if (log.isDebugEnabled()) log.debug("Trying action=" + action.toString() + " on route=" + route.getId());
			switch (action) {
				case RESUME:
					camelContext.resumeRoute(route.getId());
					break;
				case START:
					camelContext.startRoute(route.getId());
					break;
				case STOP:
					camelContext.stopRoute(route.getId());
					break;
				case SUSPEND:
					camelContext.suspendRoute(route.getId());
					break;
				default:
					break;
			}
			if (log.isInfoEnabled()) log.info("ACTION route=" + route + " action=" + action.toString());
		} catch (Exception e) {
			log.error("ACTION route=" + route + "action=" + action.toString(), e);
		}
	}

	private enum ActionEnum {
		SUSPEND, START, STOP, RESUME
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
