package com.boot.power.common.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * LoginInterceptor.java
 * 登录拦截器
 *
 * @author: zhaoxiaoping
 * @date: 2019/12/25
 **/
@Component
public class MyInterceptor implements HandlerInterceptor {

	public static final Logger logger = LoggerFactory.getLogger(MyInterceptor.class);

	/**
	 * 在请求处理之前调用,只有返回true才会执行请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Object handler) throws Exception {
		logger.info("perHandle");
		return true;
	}
}