package com.github.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StudentDTO {
	
	@NotNull // 不允许为空
    @Size(min = 1, max = 20) // 长度或大小范围
    private String name;
	
	private String stuNo;

	@Override
	public String toString() {
		return "StudentDTO [name=" + name + ", stuNo=" + stuNo + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	
	

}
