package org.restcomm.slee.ra.httpclient.nio.demo;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.slee.ActivityContextInterface;
import javax.slee.CreateException;
import javax.slee.RolledBackContext;
import javax.slee.SbbContext;
import javax.slee.facilities.Tracer;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.restcomm.slee.ra.httpclient.nio.events.HttpClientNIOResponseEvent;
import org.restcomm.slee.ra.httpclient.nio.ratype.HttpClientNIOActivityContextInterfaceFactory;
import org.restcomm.slee.ra.httpclient.nio.ratype.HttpClientNIORequestActivity;
import org.restcomm.slee.ra.httpclient.nio.ratype.HttpClientNIOResourceAdaptorSbbInterface;

/**
 * 
 * @author martins
 * 
 */
public abstract class HttpClientNIODemoSbb implements javax.slee.Sbb {

	private Tracer tracer;
	private SbbContext sbbContext;
	private HttpClientNIOActivityContextInterfaceFactory httpClientACIF;
	private HttpClientNIOResourceAdaptorSbbInterface httpClientRA;

	public void setSbbContext(SbbContext context) {

		this.sbbContext = context;
		this.tracer = sbbContext.getTracer(HttpClientNIODemoSbb.class
				.getSimpleName());
		try {
			Context ctx = (Context) new InitialContext()
					.lookup("java:comp/env");
			httpClientACIF = (HttpClientNIOActivityContextInterfaceFactory) ctx
					.lookup("slee/resources/http-client-nio/acifactory");
			httpClientRA = (HttpClientNIOResourceAdaptorSbbInterface) ctx
					.lookup("slee/resources/http-client-nio/sbbinterface");
		} catch (NamingException ne) {
			tracer.severe("Could not set SBB context:", ne);
		}
	}

	// Event handler methods

	public void onStartServiceEvent(
			javax.slee.serviceactivity.ServiceStartedEvent event,
			ActivityContextInterface aci) {
		tracer.info("Retreiving www.telestax.com...");
		try {
			HttpClientNIORequestActivity activity = httpClientRA.execute(
					new HttpGet("http://www.telestax.com"), null,
					System.currentTimeMillis());
			httpClientACIF.getActivityContextInterface(activity).attach(
					sbbContext.getSbbLocalObject());
		} catch (Exception e) {
			tracer.severe("failed to retrieve webpage", e);
		}
	}

	public void onHttpClientNIOResponseEvent(HttpClientNIOResponseEvent event,
			ActivityContextInterface aci) {
		HttpResponse response = event.getResponse();
		tracer.info("Receieved response in "
				+ (System.currentTimeMillis() - ((Long) event
						.getApplicationData())) + "ms, status: "
				+ response.getStatusLine().getStatusCode());
		try {
			tracer.info("Response content length = "
					+ EntityUtils.toString(response.getEntity()).length());
		} catch (Exception e) {
			tracer.severe("Failed reading response body", e);
		}
	}

	// Unused SBB lifecycle methods

	public void sbbActivate() {
	}

	public void sbbCreate() throws CreateException {
	}

	public void sbbExceptionThrown(Exception arg0, Object arg1,
			ActivityContextInterface arg2) {
	}

	public void sbbLoad() {
	}

	public void sbbPassivate() {
	}

	public void sbbPostCreate() throws CreateException {
	}

	public void sbbRemove() {
	}

	public void sbbRolledBack(RolledBackContext arg0) {
	}

	public void sbbStore() {
	}

	public void unsetSbbContext() {
	}

}
