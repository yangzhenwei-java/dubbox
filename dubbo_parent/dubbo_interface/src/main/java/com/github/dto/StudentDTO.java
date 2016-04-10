package com.github.dto;

public class StudentDTO {
	
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
