package de.isys.software.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple logging processor
 * <p>
 * Created by stefanhaupt on 27.07.2016.
 */
public class LoggingProcessor implements Processor {

	private Log log = LogFactory.getLog(LoggingProcessor.class);

	/**
	 * Define a custom log message, to be displayed before everything else
	 */
	String logMessage = null;

	/**
	 * Define a header to be logged; if undefined, all headers are going to be logged
	 */
	String logHeader = null;

	@Override
	public void process(Exchange exchange) throws Exception {
		StringBuilder b = new StringBuilder();
		if (logMessage != null) {
			b.append(logMessage+" ");
		}
		if (logHeader != null && logHeader.length() > 0) {
			// Logging specific Headers:
			b.append(logHeader + "=" + exchange.getIn().getHeader(logHeader) + ";");
		} else {
			// logging all headers
			exchange.getIn().getHeaders().forEach((key, value) -> b.append(key + "=" + value + "; "));
		}
		log.info("Processing exchange: " + b.toString());
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public String getLogHeader() {
		return logHeader;
	}

	public void setLogHeader(String logHeader) {
		this.logHeader = logHeader;
	}
}
