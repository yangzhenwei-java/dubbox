package com.github.event;

import com.github.dto.UserDTO;

public interface UserNotify {
	
	/**
	 * 
	 * @param dto 返回值
	 * @param id  传入参数
	 */
	public void onreturn(UserDTO dto, Integer id);
	
	/**
	 * 
	 * @param ex  异常信息
	 * @param id  传入参数
	 */
    public void onthrow(Throwable ex,  Integer id);
}
