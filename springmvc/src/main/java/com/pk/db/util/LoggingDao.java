package com.pk.db.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
//AOP 클래스라는 어노테이션
@Aspect
public class LoggingDao {

	@Around("execution(public * com.pk.db..*Dao.*(..))")
	public Object invoke(ProceedingJoinPoint joinPoint) {
		Calendar cal = new GregorianCalendar();
		Date date = new Date(cal.getTimeInMillis());
		System.out.println(date + " 데이터베이스에 접근");
		
		Object obj = null;
		try {
			obj = joinPoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return obj;
	}
}
