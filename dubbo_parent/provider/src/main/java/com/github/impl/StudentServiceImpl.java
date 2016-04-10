package com.github.impl;

import com.github.dto.StudentDTO;
import com.github.rest.StudentService;

public class StudentServiceImpl implements StudentService {

	@Override
	public void registerUser(StudentDTO studen) {
		System.err.println("学生注册了");
		System.out.println(studen);
	}

}
