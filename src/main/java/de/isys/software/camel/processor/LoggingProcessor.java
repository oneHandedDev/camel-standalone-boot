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

	Log log = LogFactory.getLog(LoggingProcessor.class);

	@Override
	public void process(Exchange exchange) throws Exception {
		StringBuilder b = new StringBuilder();
		exchange.getIn().getHeaders().forEach((key, value) -> b.append(key + "=" + value + "; "));
		log.info("Processing exchange message " + b.toString());
	}
}
