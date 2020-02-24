package com.pk.db.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pk.db.domain.Member;

@Repository
public class MemberDao {
	//하이버네이트 사용 관련 객체를 주입받기
	@Autowired
	private SessionFactory sessionFactory; 
	
	//로그인 관련 메소드
	//id와 pw를 매개변수로 받아서 일치하는 데이터가 있는지 찾아옵니다.
	public Member login(Member member) {
		return sessionFactory.getCurrentSession()
				.get(Member.class, member.getUserid());
	}
}













