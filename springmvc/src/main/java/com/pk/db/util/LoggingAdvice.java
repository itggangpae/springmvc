package com.pk.db.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

//Spring에서는 DTO 클래스를 제외하고는 클래스 이름 위에 어노테이션을 추가해서
//Bean을 자동생성하도록 합니다.
@Component
public class LoggingAdvice {
	//리턴 타입과 매개변수는 변경할 수 없습니다.
	public Object invoke(ProceedingJoinPoint joinPoint) {
		//현재 시간을 출력
		Calendar cal = new GregorianCalendar();
		Date date = new Date(cal.getTimeInMillis());
		System.out.println(date + " 요청이 발생함");
		
		Object obj = null;
		try {
			//원래 호출할 메소드
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		return obj;
	}
}









