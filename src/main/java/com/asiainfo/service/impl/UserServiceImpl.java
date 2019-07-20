package com.asiainfo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asiainfo.controller.UserController;
import com.asiainfo.dao.IUserDao;
import com.asiainfo.entity.User;
import com.asiainfo.service.interfaces.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserDao userDao;
	// 由于springmvc在web.xml里面配置了父子容器，所以在service里面调用controller的bean是不行的
//	@Autowired
//	private UserController userController;

	public void createUser(User user) {
		userDao.save(user);
	}
	
//	public void test() {
//		userController.createUser(null);
//	}

}
