package com.github.rest;

import javax.ws.rs.Path;

import com.github.dto.ValidationDTO;

@Path("validate")
public interface MyValidationService {
	
	@Path("save")
	@interface Save{}
	public void save(ValidationDTO dto);

}
