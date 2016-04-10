package com.github.impl;


import com.github.service.AnimalService;

public class TigerServiceImpl implements AnimalService {

	@Override
	public String eat() {
		

		System.out.println("吃肉......");
		
		return "关于老虎的服务被调用了....";
	}

}
