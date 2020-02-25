package com.pk.db.service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.db.dao.MemberDao;
import com.pk.db.domain.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Override
	@Transactional
	public Member login(HttpServletRequest request) {
		Member member = null;
		
		//파라미터 읽기
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		
		//member에 인스턴스를 대입하고 userid를 설정
		member = new Member();
		member.setUserid(userid);
		
		//userid에 해당하는 데이터 찾아오기
		member = memberDao.login(member);
		//userid에 해당하는 데이터가 있다면
		if(member != null) {
			if(member.getUserpw().equals(userpw)) {
				//로그인 성공한 경우는 session에 로그인 정보를 저장
				
				//하이버네이트에서 넘어온 데이터는 하이버네이트 구문으로 수정해야 함
				//member.setUserpw(null);
				request.getSession().setAttribute("member", member);
				System.out.println(member);
			}else {
				//로그인 실패
				member = null;
			}
		}	
		return member;
	}
}








