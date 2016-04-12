package com.github.extension;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;

public class ServerSecurityFilter implements ContainerRequestFilter, ContainerResponseFilter{

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		System.err.println("filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) 调用了");
		System.err.println(responseContext.getStatusInfo().toString());
		
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		System.err.println(requestContext.getHeaders().toString());
		System.err.println("filter(ContainerRequestContext requestContext)");
		
	}

}
