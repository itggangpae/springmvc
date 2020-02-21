package com.pk.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pk.db.domain.Item;
import com.pk.db.service.ItemService;

//RestController를 만들어주는 어노테이션
@RestController
public class JsonController {
	//Service 자동 주입
	@Autowired
	private ItemService itemService;
	
	//문자열을 리턴하면 문자열이 출력
	@RequestMapping(value="/item.csv", method=RequestMethod.GET)
	public String csv(HttpServletRequest request) {
		List<Item> list = itemService.allItem(request);
		//실제 csv 형식의 파일을 만들려면 문자열을 편집
		return list.toString();
	}
	
	//기본형이나 String 또는 Date 클래스를 제외한 형태로 리턴하면 json으로 응답
	@RequestMapping(value="/item2.json", method=RequestMethod.GET)
	public List<Item> json(HttpServletRequest request) {
		List<Item> list = itemService.allItem(request);
		//실제 csv 형식의 파일을 만들려면 문자열을 편집
		return list;
	}

	//itemid를 파라미터로 받아서 중복체크를 해서 리턴하는 메소드
	@RequestMapping(value="/itemidcheck", method=RequestMethod.GET)
	public Map<String, Object> itemIdCheck(
		HttpServletRequest request, 
		@RequestParam("itemid") int itemid){
		//서비스 메소드 호출
		Item item = itemService.getItem(request, itemid);
		//리턴할 Map을 생성
		Map<String, Object> map = new HashMap<String, Object>();
		if(item == null) {
			map.put("result", "true");
		}else {
			map.put("result", "false");
		}
		return map;
	}
}














