package com.github.impl;

import com.github.dto.ValidationDTO;
import com.github.rest.MyValidationService;

public class MyValidationServiceImpl implements MyValidationService {

	@Override
	public void save(ValidationDTO dto) {
		System.err.println(this.getClass().getName()+"被调用了");
		System.err.println(dto);
	}

}
