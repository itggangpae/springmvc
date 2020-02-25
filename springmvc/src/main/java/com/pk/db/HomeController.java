package com.pk.db;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pk.db.domain.Item;
import com.pk.db.domain.ItemReport;
import com.pk.db.domain.Member;
import com.pk.db.service.ItemService;
import com.pk.db.service.MemberService;


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
	
	@RequestMapping(value="/item.xml", method=RequestMethod.GET)
	public String xml(HttpServletRequest request, Model model) {
		//서비스 메소드 호출
		List<Item> list = itemService.allItem(request);
		//XML 출력 데이터 생성
		ItemReport itemReport = new ItemReport();
		itemReport.setList(list);
		
		//데이터 저장
		model.addAttribute("list", itemReport);
		//뷰이름을 리턴
		return "xmlreport";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(HttpServletRequest request, Model model) {
		//뷰이름을 리턴
		return "insert";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
			MultipartHttpServletRequest request, 
			Model model) {
		//서비스의 메소드를 호출
		itemService.insertItem(request);
		
		//삽입, 삭제, 갱신 등 새로고침을 했을 때 이전 작업을 다시 수행하면 안되는 경우에는
		//리다이렉트를 해야 합니다.
		//리다이렉트를 할 때는 View 이름이 아니고 URL을 작성
		return "redirect:./";
	}
	
	//chat 이라는 요청이 오면 chat이라는 문자열을 가지고 ViewResolver 설정을 확인해서
	//뷰 페이지를 결정 - WEB-INF/views/?.jsp
	@RequestMapping(value="/chat", method=RequestMethod.GET)
	public String chat(HttpServletRequest request, Model model) {
		//뷰이름을 리턴
		return "chat";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request, Model model) {
		//뷰이름을 리턴
		return "login";
	}
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(HttpServletRequest request, Model model,
			RedirectAttributes attrs) {
		//RedirectAttributes는  redirect 할 때 1번만 사용하는 데이터를
		//저장할 수 있는 Spring이 제공하는 클래스
		
		//서비스의 메소드 호출
		Member member = memberService.login(request);
		//로그인 처리도 redirect로 이동
		if(member == null) {
			//로그인 실패의 경우 msg를 저장하고 login으로 다시 이동
			attrs.addFlashAttribute(
				"msg", "없는 아이디이거나 잘못된 비밀번호입니다.");
			return "redirect:login";
		}else {
			//이동할 URL이 있는지 확인
			String dest = 
				(String)request.getSession().getAttribute("dest");
			if(dest != null) {
				//dest에 저장된 내용을 삭제 - 계속 남아서 문제를 발생 시킬 가능성이 있음
				request.getSession().removeAttribute("dest");
				return "redirect:" + dest;
			}
			
			//로그인 성공이면 메인 페이지로 이동
			return "redirect:./";
		}
	}
	
	
	//로그아웃을 처리하는 메소드
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request, 
		HttpSession session) {
		//Session 객체 만들기
		//request.getSession() 해도 되고 Controller 의 요청 처리 메소드에
		//매개변수로 추가해도 됩니다.
		
		//세션 초기화
		session.invalidate();
		//로그인 과 로그아웃도 redirect를 합니다.
		return "redirect:./";
	}
}














