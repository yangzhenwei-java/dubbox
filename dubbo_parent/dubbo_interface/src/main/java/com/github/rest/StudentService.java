package com.github.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.github.dto.StudentDTO;

@Path("student")
public interface StudentService {
		
    @POST
    @Path("register")
    @Consumes({MediaType.APPLICATION_JSON})
    public  void registerUser(StudentDTO studen) ;
}
