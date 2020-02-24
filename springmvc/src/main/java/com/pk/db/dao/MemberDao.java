package com.pk.db.dao;

import java.util.List;

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
	//id를 매개변수로 받아서 일치하는 데이터가 있는지 찾아옵니다.
	public Member login(Member member) {
		//userid 가 기본키가 아니라서 
		//SQL을 이용해서 직접 조회
		List<Member> list = 
			(List<Member>)sessionFactory.getCurrentSession()
			.createSQLQuery("select * from member where userid=:userid")
			.addEntity(Member.class)
			.setString("userid", member.getUserid())
			.list();
		//조회된 데이터가 없으면 null을 리턴하고 조회된 데이터가 있으면 데이터를 리턴
		if(list.size() == 0) {
			return null;
		}else {
			return list.get(0);
		}
	}
}













