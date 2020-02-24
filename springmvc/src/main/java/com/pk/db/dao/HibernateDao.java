package com.pk.db.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pk.db.domain.Item;

//Bean(스프링이 생성하는 인스턴스)을 자동생성해주고 
//데이터 관련된 클래스라는 알려주기 위한 어노테이션
@Repository
public class HibernateDao {
	//하이버네이트를 사용할 수 있는 인스턴스를 주입
	@Autowired
	private SessionFactory sessionFactory;
	
	//전체 데이터 가져오는 메소드
	public List<Item> allItem(){
		return sessionFactory.getCurrentSession()
				.createCriteria(Item.class).list();
	}
	
	//데이터 1개를 가져오는 메소드
	public Item getItem(int itemid) {
		return sessionFactory.getCurrentSession()
				.get(Item.class, itemid);
	}
	
	//데이터 1개를 삽입하는 메소드
	public int insertItem(Item item) {
		sessionFactory.getCurrentSession().save(item);
		return 1;
	}
}












