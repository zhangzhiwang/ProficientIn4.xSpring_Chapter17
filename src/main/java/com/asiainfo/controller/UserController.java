package com.asiainfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
//@RequestMapping("/user") // 所有请求的URL为“部署跟路径/user/...”的全部被转发给该处理器处理
//@RequestMapping("/user/*/b") // 一个*只代表任意一级目录，注意是有且只有一级目录，不能没有也不能有多级目录
//@RequestMapping("/user/**/b") // 两个*代表任意多级目录，可以没有也可以有多个
//@RequestMapping("/user/b?") // ?代表一个字符，注意是有且只有一个字符，不能没有
//@RequestMapping("/user/{userId}") // {xxx}代表占位符，注意前面没有“$”和“#”，直接就是“{xxx}”
@RequestMapping("/user/*/aaa/**/{userId}/b/{userName}") // 综合运用上面的
public class UserController {
	@Autowired // 如果有多个实现类，默认按类型注入，如果发现多个则按照名称注入，如果没有指定名称的bean，则抛错：No qualifying bean of type [com.asiainfo.service.interfaces.IUserService] is defined: expected single matching bean but found 2: userServiceImpl2,userServiceImpl
	private IUserService userService;
	
	@RequestMapping("/regist")	// 负责处理URL为“部署跟路径/user/regist.html”的请求，即如果类定义处有@RequestMapping，则方法定义处的@RequestMapping所指定的路径是类定义处的下一级，若类定义处未指定@RequestMapping，则方法定义处的@RequestMapping也是相对于部署跟路径的
	public String regist(@PathVariable("userId") String userId, @PathVariable("userName") String userName) {
		System.out.println(userId);
		System.out.println(userName);
		System.out.println("--------------");
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
