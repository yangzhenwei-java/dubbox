package com.github.impl;

import com.github.service.AnimalService;

public class HorseServiceImpl implements AnimalService {

	@Override
	public String eat() {
		
		System.out.println("吃草....");
		
		return "关于马儿的服务调用了";
	}

}
