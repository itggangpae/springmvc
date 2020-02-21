package com.pk.db;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pk.db.domain.Item;
import com.pk.db.service.ItemService;


@Controller
public class HomeController {
	//Service 객체를 주입
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {;
		//서비스 메소드 호출
		List<Item> list = itemService.allItem(request);
		//데이터 저장
		model.addAttribute("list", list);
	
		return "home";
	}
	
	@RequestMapping(value = "/detail/{itemid}", method = RequestMethod.GET)
	public String detail(HttpServletRequest request, Model model,
			@PathVariable int itemid) {;
		//서비스 메소드 호출
		Item item = itemService.getItem(request, itemid);
		//데이터 저장
		model.addAttribute("item", item);
	
		return "detail";
	}
	
	@RequestMapping(value="/item.xls", method=RequestMethod.GET)
	public String excel(HttpServletRequest request, Model model) {
		//서비스 메소드 호출
		List<Item> list = itemService.allItem(request);
		//데이터 저장
		model.addAttribute("list", list);
		//뷰이름을 리턴
		return "excel";
	}
	
	@RequestMapping(value="/item.pdf", method=RequestMethod.GET)
	public String pdf(HttpServletRequest request, Model model) {
		//서비스 메소드 호출
		List<Item> list = itemService.allItem(request);
		//데이터 저장
		model.addAttribute("list", list);
		//뷰이름을 리턴
		return "pdf";
	}
	
	@RequestMapping(value="/item1.json", method=RequestMethod.GET)
	public String item1json(HttpServletRequest request, Model model) {
		//서비스 메소드 호출
		List<Item> list = itemService.allItem(request);
		//데이터 저장
		model.addAttribute("list", list);
		//뷰이름을 리턴
		return "item1json";
	}
}






