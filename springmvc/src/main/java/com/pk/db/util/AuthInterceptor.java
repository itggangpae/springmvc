package com.pk.db.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//클래스 이름에 Adapter가 붙는 클래스는
//Adaptor를 제외한 인터페이스가 존재하는데
//인터페이스는 모든 메소드를 재정의 해야 하고 Adapter 클래스는 필요한 메소드만
//재정의하면 됩니다.

//Bean을 자동 생성해주는 어노테이션:Component, Controller, Service, Repository
//RestController
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(
		HttpServletRequest request, 
		HttpServletResponse response,
		Object handle) {
		boolean result = false;
		
		//로그인이 안된 것을 확인
		if(request.getSession().getAttribute("member") != null) {
			result = true;
		}else {
			try {
				
				//로그인 페이지로 이동할 때 메시지 저장
				request.getSession().setAttribute(
					"msg", "로그인이 되어야 가능한 서비스입니다.");
				//요청 URL을 확인
				String requestURI = request.getRequestURI();
				String contextPath = request.getContextPath();
				String command = requestURI.substring(contextPath.length());
				
				//파라미터 가져오기
				String queryString = request.getQueryString();
				//파라미터가 있으면 command 뒤에 붙이기
				if(queryString != null) {
					command = command + "?" + queryString;
				}
				
				//세션에 command 저장
				request.getSession().setAttribute("dest", command);
				
				//로그인이 되어 있지 않으면 로그인 페이지로 이동
				response.sendRedirect("login");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public void postHandle(
		HttpServletRequest request, 
		HttpServletResponse response,
		Object handle,
		ModelAndView mav) {
		System.out.println("Controller의 요청을 정상적으로 처리");
		//로그 기록하는 경우가 많습니다.
	}
}






