package com.asiainfo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.asiainfo.controller.UserController;

public class SpringMVCTest {
	public static void main(String[] args) {
		// 测试父子容器
		ApplicationContext applicationContextParent = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//		ApplicationContext applicationContextChild = new ClassPathXmlApplicationContext(new String[] {"classpath:applicationContextChild.xml"}, applicationContextParent);
		UserController userController = (UserController) applicationContextParent.getBean("userController");
		userController.createUser(null);
	}
}
