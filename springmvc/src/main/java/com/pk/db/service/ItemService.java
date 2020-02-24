package com.pk.db.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pk.db.domain.Item;

public interface ItemService {
	//전체 데이터를 가져오는 메소드
	public List<Item> allItem(HttpServletRequest request);
	
	//메소드 오버로딩: 메소드의 이름은 같고 매개변수의 개수나 자료형이 다른 경우
	//데이터 1개를 가져오는 메소드
	public Item getItem(HttpServletRequest request);
	//파라미터를 읽지 않고 받는 메소드
	public Item getItem(HttpServletRequest request, int itemid);
	
	//데이터 삽입(파일 업로드) 처리를 위한 메소드
	public int insertItem(MultipartHttpServletRequest request);
	
}











