package com.github.event;

import com.github.dto.UserDTO;
import com.github.event.UserNotify;

public class UserNotifyImpl implements UserNotify{
	
	@Override
	public void onreturn(UserDTO dto, Integer id) {
		System.err.println("事件通知返回结束.传入参数:"+id+";结果返回："+dto);
		
	}
	
	@Override
	public void onthrow(Throwable ex, Integer id) {
		
		System.err.println("事件通知异常了,传入参数："+id+";异常信息："+ex.getMessage());
	}

}
