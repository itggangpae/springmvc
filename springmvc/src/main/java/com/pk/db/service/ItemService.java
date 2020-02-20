package com.pk.db.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.pk.db.domain.Item;

public interface ItemService {
	//전체 데이터를 가져오는 메소드
	public List<Item> allItem(HttpServletRequest request);
	
	//데이터 1개를 가져오는 메소드
	public Item getItem(HttpServletRequest request);
}
