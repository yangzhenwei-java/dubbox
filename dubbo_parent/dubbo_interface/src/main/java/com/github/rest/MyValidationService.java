package com.github.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import com.github.dto.ValidationDTO;

@Path("validate")
public interface MyValidationService {
	@POST
	@Path("save")
	@Consumes({MediaType.APPLICATION_JSON})
	public void save(ValidationDTO dto);

}
