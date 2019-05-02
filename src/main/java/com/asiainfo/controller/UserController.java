package com.asiainfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.entity.User;
import com.asiainfo.service.interfaces.IUserService;

/**
 * 用户控制器
 *
 * @author zhangzhiwang
 * @date May 1, 2019 1:37:30 PM
 */
@Controller // 声明为spring的bean，同时作为可以处理http请求的spring mvc的处理器
@RequestMapping("/user") // 所有请求的URL为“部署跟路径/user/...”的全部被转发给该处理器处理
public class UserController {
	@Autowired // 如果有多个实现类，默认按类型注入，如果发现多个则按照名称注入，如果没有指定名称的bean，则抛错：No qualifying bean of type [com.asiainfo.service.interfaces.IUserService] is defined: expected single matching bean but found 2: userServiceImpl2,userServiceImpl
	private IUserService userService;
	
	@RequestMapping("/regist")	// 负责处理URL为“部署跟路径/user/regist.html”的请求
	public String regist() {
		return "user/register";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ModelAttribute("user")
	public ModelAndView createUser(User user) {
		userService.createUser(user);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/createSuccess");
		modelAndView.addObject("user", user);
		return modelAndView;
	}
}
