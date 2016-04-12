package com.github.service;

import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.dto.StudentDTO;
import com.github.dto.UserDTO;
@Path("dubbo")
public interface BaseProviderService {
	@GET
	@Path("getUser")
	@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
	public UserDTO getUser(Long id);
	
	@GET
	@Path("{id : \\d+}")
	@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
	public UserDTO getUser1( @PathParam("id") @Min(value=1L, message="User ID must be greater than 1") Long id);
	
    @POST
    @Path("register")
    @Consumes({MediaType.APPLICATION_JSON})
    public  void registerUser(StudentDTO studen) ;
}
