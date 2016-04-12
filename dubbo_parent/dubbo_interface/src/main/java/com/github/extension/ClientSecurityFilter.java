package com.github.extension;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

public class ClientSecurityFilter implements ClientRequestFilter, ClientResponseFilter{

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		System.err.println(requestContext.getEntity().toString());
		System.err.println(requestContext.getMethod());
		System.err.println(requestContext.getDate());
		System.err.println(requestContext.getUri());
		System.err.println(requestContext.hasEntity());
		System.err.println(requestContext.getHeaders().size());
		System.err.println(requestContext.getMediaType().toString());
		System.err.println(requestContext.getCookies().size());
		System.err.println("filter(ClientRequestContext requestContext, ClientResponseContext responseContext) 被调用了");
		System.err.println(responseContext.getLength());
		System.err.println(responseContext.getStatus());
		System.err.println(responseContext.hasEntity());
		System.err.println(responseContext.getCookies().size());
//		System.err.println(responseContext.getHeaders().size());
		System.err.println(responseContext.getLocation().toString());
		
	}

	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		
		System.err.println("filter(ClientRequestContext requestContext) 被调用了");
	}

}
