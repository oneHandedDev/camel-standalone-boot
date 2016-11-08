package de.isys.software.camel.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Simple Status-Code Processor
 * <p>
 * This {@link Processor} is designed to be called via Apache Camel-DSL.
 * Created by stefanhaupt on 27.07.2016.
 */
public class HttpStatusCodeProcessor implements Processor {

	private Log log = LogFactory.getLog(HttpStatusCodeProcessor.class);

	private int httpStatusCode;

	private String httpBody = null;

	/**
	 * Sets HTTP-Status-Code on Out-Object in Exchange to defined Status-Code
	 *
	 * @param exchange
	 * @throws Exception
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		if (log.isWarnEnabled()) {
			log.warn("Processing Exchange-Message " + exchange.toString() + " with StatusCode: " + httpStatusCode);
		}
		Exception exception = exchange.getException();
		if (exception != null && log.isWarnEnabled()) {
			log.warn("Exception caught: ", exception);
		}
		exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, getHttpStatusCode());
		if (getHttpBody() != null) {
			exchange.getOut().setBody(getHttpBody());
		}
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getHttpBody() {
		return httpBody;
	}

	public void setHttpBody(String httpBody) {
		this.httpBody = httpBody;
	}
}
