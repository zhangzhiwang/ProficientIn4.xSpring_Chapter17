package com.asiainfo.service.impl;

import org.springframework.stereotype.Service;

import com.asiainfo.entity.User;
import com.asiainfo.service.interfaces.IUserService;

//@Service
public class UserServiceImpl2 implements IUserService {

	public void createUser(User user) {
		System.out.println("2");
	}

}
