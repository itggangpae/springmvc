package com.pk.db.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pk.db.dao.ItemDao;
import com.pk.db.domain.Item;

//Bean을 자동생성해주는 어노테이션
@Service
public class ItemServiceImpl implements ItemService {
	//ItemDao 타입의 Bean이 있으면 자동으로 주입시켜주는 어노테이션
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> allItem(HttpServletRequest request) {
		//Dao의 메소드에 파라미터가 없는 경우는 Dao 메소드를 호출해서 리턴
		return itemDao.allItem();
	}

	@Override
	public Item getItem(HttpServletRequest request) {
		//파라미터 읽어오기
		String itemid = request.getParameter("itemid");
		//파라미터를 정수로 변환해서 Dao 메소드를 호출
		return itemDao.getItem(Integer.parseInt(itemid));
	}

	@Override
	public Item getItem(HttpServletRequest request, int itemid) {
		return itemDao.getItem(itemid);
	}

}



