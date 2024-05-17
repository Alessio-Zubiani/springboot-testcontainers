package com.example.activemq.ws.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WebServiceClient extends WebServiceGatewaySupport {

	public Object callWebService(String url, Object request) {
	    return getWebServiceTemplate().marshalSendAndReceive(url, request);
	}
	
}
