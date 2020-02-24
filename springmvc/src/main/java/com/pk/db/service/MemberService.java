package com.pk.db.service;

import javax.servlet.http.HttpServletRequest;

import com.pk.db.domain.Member;

public interface MemberService {
	//로그인 처리 메소드
	public Member login(HttpServletRequest request);
}
