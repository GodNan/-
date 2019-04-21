
package com.css.bdpfnew.interceptor;

import com.css.bdpfnew.BodyReaderHttpServletRequestWrapper;
import com.css.bdpfnew.Message;
import com.css.bdpfnew.util.SignatureUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Interceptor - 接口安全
 * 
 * @author GodNan
 * @version 1.0
 */
@CommonsLog
public class SecurityInterceptor extends HandlerInterceptorAdapter {

	/**
	 * api验证控制开关
	 */
	// TODO:这个参数应该重构到配置文件中；不知道为什么@Value注解再次不生效，导致无法注入
	private static final boolean STATE = false;

	/** 默认忽略参数 */
	private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[] { "password", "rePassword",
			"currentPassword" };

	/** antPathMatcher */
	private static AntPathMatcher antPathMatcher = new AntPathMatcher();

	/** 忽略参数 */
	private String[] ignoreParameters = DEFAULT_IGNORE_PARAMETERS;

	/**
	 * 进入controller方法之前
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!STATE) {
			return true;
		}
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		request.getParameterMap();
		String tokenClient = request.getHeader("CSRFToken");
		String timestamp = request.getHeader("timestamp");
		String sign = request.getHeader("sign");

		String fileUpload = request.getHeader("fileUpload");

		if (StringUtils.isEmpty(tokenClient)) {
			out = response.getWriter();
			out.append(Message.error("token不能为空", Message.ERROR_CODE_EMPTY_DATA).toString());
			out.close();
			return false;
		}
		if (StringUtils.isEmpty(timestamp)) {
			out = response.getWriter();
			out.append(Message.error("timestamp不能为空", Message.ERROR_CODE_EMPTY_DATA).toString());
			out.close();
			return false;
		}

		Long nowTime = new Date().getTime();
		if (nowTime - Long.valueOf(timestamp) > 30 * 60 * 1000) {
			out = response.getWriter();
			out.append(Message.error("请求过期", Message.ERROR_CODE_REQUEST_EXPIRED).toString());
			out.close();
			return false;
		}

		if (!StringUtils.isEmpty(fileUpload)) {
			return true;
		}

		if (StringUtils.isEmpty(sign)) {
			out = response.getWriter();
			out.write(Message.error("sign不能为空", Message.ERROR_CODE_EMPTY_DATA).toString());
			out.close();
			return false;
		}

		String signContrast = null;
		String parameters = new BodyReaderHttpServletRequestWrapper(request).getBodyString(request);
		if (StringUtils.isNotEmpty(parameters)) {
			// 参数在请求体中
			signContrast = SignatureUtil.generateToBody(parameters, tokenClient, Long.valueOf(timestamp));

		} else {
			// 参数在路径之中,对整个uri进行加密对比
			String requestURI = request.getRequestURI();
			signContrast = SignatureUtil.generateToUri(requestURI, tokenClient, Long.valueOf(timestamp));
		}

		if (!sign.equals(signContrast)) {
			out = response.getWriter();
			out.write(Message.error("请求不合法", Message.ERROR_CODE_REQUEST_EXPIRED).toString());
			out.close();
			return false;
		}
		return true;

	}

	@Override
	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
	}

	@Override
	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		log.info("---------------视图渲染之后的操作-------------------------0");
	}

}