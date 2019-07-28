package com.asiainfo.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.asiainfo.entity.Dept;
import com.asiainfo.entity.User;
import com.asiainfo.service.interfaces.IUserService;

/**
 * 用户控制器
 *
 * @author zhangzhiwang
 * @date May 1, 2019 1:37:30 PM
 */
// @Controller // 声明为spring的bean，同时作为可以处理http请求的spring mvc的处理器
// @RequestMapping("/user") // 所有请求的URL为“部署跟路径/user/...”的全部被转发给该处理器处理
// @RequestMapping("/user/*/b") // 一个*只代表任意一级目录，注意是有且只有一级目录，不能没有也不能有多级目录
// @RequestMapping("/user/**/b") // 两个*代表任意多级目录，可以没有也可以有多个
// @RequestMapping("/user/b?") // ?代表一个字符，注意是有且只有一个字符，不能没有
// @RequestMapping("/user/{userId}") // {xxx}代表占位符，注意前面没有“$”和“#”，直接就是“{xxx}”
// @RequestMapping("/user/*/aaa/**/{userId}/b/{userName}") // 综合运用上面的
// @Service // 经实际测试，标注其他同类型的注解Spring MVC仍然会把UserController当作控制器，但为了可读性不要这样做
// @Component
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired // 如果有多个实现类，默认按类型注入，如果发现多个则按照名称注入，如果没有指定名称的bean，则抛错：No qualifying bean of type
				// [com.asiainfo.service.interfaces.IUserService] is defined: expected single
				// matching bean but found 2: userServiceImpl2,userServiceImpl
	private IUserService userService;

	@RequestMapping("/regist") // 负责处理URL为“部署跟路径/user/regist.html”的请求，即如果类定义处有@RequestMapping，则方法定义处的@RequestMapping所指定的路径是类定义处的下一级，若类定义处未指定@RequestMapping，则方法定义处的@RequestMapping也是相对于部署跟路径的
	public String regist() {
		return "user/register";
	}

	@RequestMapping("/regist2/{userId}/{un}")
	public String regist2(@PathVariable String userId, @PathVariable("un") String userName) {// @PathVariable后面最好要绑定url的占位符的名称以免换一个运行环境（比如到服务器上）出现意想不到的问题
		System.out.println(userId);
		System.out.println(userName);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist3", method = RequestMethod.POST, params = { "p1=1", "p2", "!p3" }, headers = {
			"content-type=text/html" })
	public String regist3(String userId, String userName) {// @PathVariable后面最好要绑定url的占位符的名称以免换一个运行环境（比如到服务器上）出现意想不到的问题
		System.out.println(userId);
		System.out.println(userName);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist4/{userId}", method = RequestMethod.GET)
	public String regist4(@PathVariable String userId, @MatrixVariable(required = false) Integer a,
			@MatrixVariable(required = false) String b) {// @PathVariable后面最好要绑定url的占位符的名称以免换一个运行环境（比如到服务器上）出现意想不到的问题
		System.out.println(userId);
		System.out.println(a);
		System.out.println(b);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist5", method = RequestMethod.GET)
	public String regist5(@RequestParam(value = "userId", defaultValue = "111") int userId,
			@RequestParam(value = "userName", required = false) String userName,
			@CookieValue(value = "_ga", required = false) String _ga, @RequestHeader("Accept-Encoding") String encoding,
			@RequestHeader("Connection") String connection) {
		System.out.println(userId);
		System.out.println(userName);
		System.out.println(_ga);
		System.out.println(encoding);
		System.out.println(connection);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist6", method = RequestMethod.GET)
	public String regist6(User user) {
		System.out.println(user);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist7", method = RequestMethod.GET)
	public String regist7(User user) {
		System.out.println(user);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist8", method = RequestMethod.GET)
	public String regist8(HttpServletRequest httpServletRequest) {
		String userId = httpServletRequest.getParameter("userId");
		String userName = (String) httpServletRequest.getAttribute("userName");// getAttribute()方法不是用来接受客户端传来的参数用的
		System.out.println(httpServletRequest);
		System.out.println(userId);
		System.out.println(userName);
		System.out.println("--------------");
		return "user/register";
	}

	@RequestMapping(value = "/regist9", method = RequestMethod.GET)
	public void regist9(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {// 如果入参中使用HttpServletResponse，返回值类型是void即可
		String userId = httpServletRequest.getParameter("userId");
		String userName = (String) httpServletRequest.getAttribute("userName");// getAttribute()方法不是用来接受客户端传来的参数用的
		System.out.println(httpServletRequest);
		System.out.println(userId);
		System.out.println(userName);
		System.out.println("--------------");

		// HttpServletResponse对象常见应用
		OutputStream outputStream = httpServletResponse.getOutputStream();
		httpServletResponse.setHeader("content-type", "text/*;charset=UTF-8");
		String data = "中国";
		outputStream.write(data.getBytes());
	}

	@RequestMapping(value = "/regist10", method = RequestMethod.GET)
	public void regist10(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			HttpSession session, @RequestParam("userName") String userName2) throws IOException {// 如果入参中使用HttpServletResponse，返回值类型是void即可
		String userName = httpServletRequest.getParameter("userName");
		System.out.println(userName);
		System.out.println(userName2);
		System.out.println(session.toString());

		System.out.println("--------------");

		// HttpServletResponse对象常见应用
		OutputStream outputStream = httpServletResponse.getOutputStream();
		httpServletResponse.setHeader("content-type", "text/*;charset=UTF-8");
		String data = "中国";
		outputStream.write(data.getBytes());
	}

	@RequestMapping(value = "/regist12", method = RequestMethod.GET)
	public void regist12(OutputStream os) throws IOException {
		Resource res = new ClassPathResource("/IMG_9752.JPG");
		FileCopyUtils.copy(res.getInputStream(), os);
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	// @ModelAttribute("user")
	public ModelAndView createUser(@ModelAttribute("user") User user) {
		userService.createUser(user);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/createSuccess");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView modelAndView = new ModelAndView();
		// 添加模型的方式1
		// User user = new User();
		// user.setUserName("zs");
		// user.setUserId(1);
		// modelAndView.addObject("u", user);// 在jsp中用${u.userName}获取数据

		// 添加模型的方式2
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "ls");
		map.put("key2", 2);
		modelAndView.addAllObjects(map);// 在jsp中用${key1}、${key2}获取数据

		modelAndView.setViewName("user/createSuccess");
		return modelAndView;
	}

	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	public ModelAndView test2(@ModelAttribute("uu") User user) {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("u", user);
		modelAndView.setViewName("user/createSuccess");
		return modelAndView;
	}

	@RequestMapping(value = "/test3", method = RequestMethod.GET)
	public String test3(@ModelAttribute("user") User user) {
		user.setLastIp("127");
		return "user/createSuccess";
	}

	@RequestMapping(value = "/test4", method = RequestMethod.GET)
	public ModelAndView test4(ModelMap modelMap) {
		User user = (User) modelMap.get("uu");
		System.out.println(user);
		user.setUserName("zzw");

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", modelMap);
		modelAndView.setViewName("user/createSuccess");
		return modelAndView;
	}

	@RequestMapping(value = "/test5", method = RequestMethod.GET)
	public ModelAndView test5(@RequestParam("userCols") User user) {
		System.out.println(user);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/showUser");
		modelAndView.addObject("user", user);

		return modelAndView;
	}

	@RequestMapping(value = "/test6", method = RequestMethod.GET)
	public void test6(@Valid User user, BindingResult bindingResult, @Valid Dept d, BindingResult bindingResult2) {// BindingResult必须紧随被@Valid注解标注的入参对象之后，中间不能插入其他入参，如果多个入参都被@Valid注解标注那么需要在每一个需要校验的入参后面加上BindingResult
		System.out.println(user);
		if (bindingResult.hasErrors()) {
			System.out.println("有错误！");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				String field = fieldError.getField();
				String defaultMessage = fieldError.getDefaultMessage();
				System.out.println("错误字段：" + field + "，错误信息:" + defaultMessage);
			}
		} else {
			System.out.println("成功");
		}

		System.out.println("------------");

		if (bindingResult2.hasErrors()) {
			System.out.println("有错误！");
			List<FieldError> fieldErrors = bindingResult2.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				String field = fieldError.getField();
				String defaultMessage = fieldError.getDefaultMessage();
				System.out.println("错误字段：" + field + "，错误信息:" + defaultMessage);
			}
		} else {
			System.out.println("成功");
		}
	}

	@RequestMapping("/regist20")
	public ModelAndView regist20() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/register3");
		return modelAndView;
	}

	@RequestMapping("/doRegist")
	public ModelAndView doRegist(User user) {
		System.out.println(user);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user/createSuccess");
		return modelAndView;
	}

	@RequestMapping("/showUserList")
	public ModelAndView showUserList() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2019, 7, 27);
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserName("zs1");
		user1.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user1);

		User user2 = new User();
		user2.setUserName("zs2");
		user2.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user2);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("user/userList");
		return modelAndView;
	}
	
	@RequestMapping("/showUserListByExcel")
	public ModelAndView showUserListByExcel() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2019, 7, 27);
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("zs1");
		user1.setPassword("1234");
		user1.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user1);

		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("zs2");
		user2.setPassword("5678");
		user2.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user2);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("userListExcelView");
		return modelAndView;
	}
	
	@RequestMapping("/showUserListByPDF")
	public ModelAndView showUserListByPDF() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2019, 7, 27);
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("zs1");
		user1.setPassword("1234");
		user1.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user1);
		
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("zs2");
		user2.setPassword("5678");
		user2.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user2);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("userListPDFView");
		return modelAndView;
	}
	
	@RequestMapping("/showUserListByXML")
	public ModelAndView showUserListByXML() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2019, 7, 27);
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("zs1");
		user1.setPassword("1234");
		user1.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user1);
		
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("zs2");
		user2.setPassword("5678");
		user2.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user2);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("userListXMLView");
		return modelAndView;
	}
	
	@RequestMapping("/showUserListByJSON")
	public ModelAndView showUserListByJSON() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2019, 7, 27);
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("zs1");
		user1.setPassword("1234");
		user1.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user1);
		
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("zs2");
		user2.setPassword("5678");
		user2.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user2);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("userListJSONView");
		return modelAndView;
	}
	
	@RequestMapping("/showUserListByMix")
	public ModelAndView showUserListByMix() {
		Calendar calendar = new GregorianCalendar();
		calendar.set(2019, 7, 27);
		List<User> userList = new ArrayList<User>();
		User user1 = new User();
		user1.setUserId(1);
		user1.setUserName("zs1");
		user1.setPassword("1234");
		user1.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user1);
		
		User user2 = new User();
		user2.setUserId(2);
		user2.setUserName("zs2");
		user2.setPassword("5678");
		user2.setLastVisit(new Timestamp(calendar.getTimeInMillis()));
		userList.add(user2);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("userList", userList);
		modelAndView.setViewName("userListMix");
		return modelAndView;
	}
	
	@RequestMapping("/upload")
	public ModelAndView upload() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("uploadPage");
		return modelAndView;
	}
	
	@RequestMapping("/doUpload")
	public ModelAndView doUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile multipartFile) throws Exception {// 上传的文件自动绑定到MultipartFile中
		ModelAndView modelAndView = new ModelAndView();
		if(multipartFile != null && !multipartFile.isEmpty()) {
			System.out.println("ContentType:" + multipartFile.getContentType());
			System.out.println("name:" + multipartFile.getName());
			System.out.println("filename:" + multipartFile.getOriginalFilename());
			System.out.println("sizw:" + multipartFile.getSize());
			
			multipartFile.transferTo(new File("/Users/zhangzhiwang/Desktop/tmp_2019_" + multipartFile.getOriginalFilename()));
			modelAndView.setViewName("redirect:/user/success.html");
			return modelAndView;
		} else {
			modelAndView.setViewName("redirect:/user/fail.html");
			return modelAndView;
		}
	}
	
	@RequestMapping("/success")
	public ModelAndView success() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	@RequestMapping("/fail")
	public ModelAndView fail() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fail");
		return modelAndView;
	}
	
	@RequestMapping("/throwException")
	public ModelAndView throwException() throws MyException {
		throw new MyException();
	}
	
	@ExceptionHandler({RuntimeException.class, MyException.class})
	public ModelAndView catchException() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("fail");
		return modelAndView;
	}

	// 添加模型的方式3
	@ModelAttribute("user") // @ModelAttribute加在方法上，Spring
							// MVC在调用任何一个具体的处理方法前先调用在方法定义出标注了@ModelAttribute注解的方法，并将返回值以uu为键放在模型中，然后再执行具体的处理方法。见课本p587
	public User getUser() {
		User user = new User();
		user.setUserId(3);
		return user;
	}
}

class MyException extends Exception {}
