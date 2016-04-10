package com.github.dto;

import java.io.Serializable;

public class UserDTO  implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6462379562312043634L;

	private Integer id;
	
	private String loginName;
	
	private String age;
	
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", loginName=" + loginName + ", age="
				+ age + ", phone=" + phone + "]";
	}
	
	
	

}
