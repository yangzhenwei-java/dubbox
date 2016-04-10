package com.github.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.rpc.RpcContext;
import com.github.dto.UserDTO;
import com.github.listener.CallBackListener;
import com.github.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	static Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	
	@Override
	public UserDTO get(Integer id) {
		
		RpcContext context = RpcContext.getContext();
		String remoteIp = context.getRemoteHost();
		String name = context.getMethodName();
		URL url = context.getUrl();
		int port = context.getLocalPort();
		log.info("调用者IP:["+remoteIp+"],调用的方法名字:["+name+"],请求的url明细:["+url+"],服务方提供服务的端口:["+port+"]");
		
		if(id>100){
			throw new RuntimeException("事件通知异常演示");
		}
		if(id.equals(1)){
			UserDTO dto = new UserDTO();
			dto.setId(1);
			dto.setLoginName("zhansan@163.com");
			dto.setPhone("11111111..");
			dto.setAge("10");
			return dto;
		}else if(id.equals(2)){
			UserDTO dto = new UserDTO();
			dto.setId(2);
			dto.setLoginName("lisi@163.com");
			dto.setPhone("22222222..");
			dto.setAge("20");
			return dto;
		}else if(id.equals(3)){
			UserDTO dto = new UserDTO();
			dto.setId(2);
			dto.setLoginName("wangwu@163.com");
			dto.setPhone("3333333..");
			dto.setAge("30");
			return dto;
		}else{
			return null;
		}
		
	}
	
	@Override
	public int save(UserDTO user) {
		
		RpcContext context = RpcContext.getContext();
		String remoteIp = context.getRemoteHost();
		String name = context.getMethodName();
		URL url = context.getUrl();
		int port = context.getLocalPort();
		log.info("调用者IP:["+remoteIp+"],调用的方法名字:["+name+"],请求的url明细:["+url+"],服务方提供服务的端口:["+port+"]");
		
		try {
			System.out.println(JSON.json(user));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
	
	@Override
	public String loadbalance() {
		RpcContext context = RpcContext.getContext();
		int port = context.getLocalPort();
		String rsp = "端口号为"+port+"服务器被调用了";
		log.info(rsp);
		return rsp;
	}

	@Override
    public void batchAddUsers(CallBackListener listener, List<UserDTO> list) throws Exception {
    	
    	System.out.println("batchAddUsers被调用了.....");
    	for(int i=0;i<=100;i++){
    		Thread.sleep(1000);
    		listener.changed(i+"%");
    	}

  }
   
	

}
