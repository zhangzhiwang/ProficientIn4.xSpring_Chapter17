package com.asiainfo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMVCTest {
	public static void main(String[] args) {
		// 测试父子容器
		ApplicationContext applicationContextParent = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		ApplicationContext applicationContextChild = new ClassPathXmlApplicationContext(new String[] {"classpath:applicationContextChild.xml"}, applicationContextParent);
		Object bean = applicationContextParent.getBean("child");
		System.out.println(bean);
	}
}
