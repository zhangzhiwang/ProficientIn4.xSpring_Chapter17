package com.asiainfo;

import org.springframework.core.convert.converter.Converter;

import com.asiainfo.entity.User;
import com.mysql.jdbc.StringUtils;

/**
 * 自定义类型转换器ConversionService
 *
 * @author zhangzhiwang
 * @date 2019年7月18日 下午9:41:27
 */
public class MyConversionService implements Converter<String, User>{

	public User convert(String source) {
		if(StringUtils.isNullOrEmpty(source)) {
			return null;
		}
		
		String[] cols = source.split(":");
		User user = new User();
		user.setUserName(cols[0]);
		user.setUserId(Integer.parseInt(cols[1]));
		
		return user;
	}
	
}
