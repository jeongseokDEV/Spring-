package com.spring.board.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggerInterceptor extends HandlerInterceptorAdapter {

	protected final Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	@SuppressWarnings("rawtypes")
	/* SuppressWarnings = 컴파일 경고를 무시함 // 
	   rawtypes = 재네릭(데이터형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있음)을 사용하는 클래스 매개 변수가 불특정일 떄의 경고 억제
	   Enumeration - 객체들의 집합에서 각각의 객체들을 한순간에 하나씩 처리할 수 있는 메소드를 제공하는 컬렉션
	*/
	@Override
	//preHandle - 전처리기 역할
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("==================== LoggerInterceptor START ====================");
		logger.debug(" URI [{}]," + request.getRequestURI());

		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String key = (String) paramNames.nextElement();
			String value = request.getParameter(key);
			logger.debug(" RequestParameter Data ==>  " + key + " : " + value + "");
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	//postHandle - 후 처리기 역할
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {

		logger.debug("==================== LoggerInterceptor END ====================");
	}
}
