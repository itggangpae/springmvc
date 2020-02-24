package com.pk.db.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.pk.db.dao.HibernateDao;
import com.pk.db.dao.ItemDao;
import com.pk.db.domain.Item;

//Bean을 자동생성해주는 어노테이션
@Service
public class ItemServiceImpl implements ItemService {
	//ItemDao 타입의 Bean이 있으면 자동으로 주입시켜주는 어노테이션
	@Autowired
	private ItemDao itemDao;
	
	//Hibernate 연동하는 Bean을 자동 주입
	//동일한 자료형의 Bean이 있으면 자동 주입
	@Autowired
	private HibernateDao hibernateDao;

	@Override
	@Transactional
	public List<Item> allItem(HttpServletRequest request) {
		//Dao의 메소드에 파라미터가 없는 경우는 Dao 메소드를 호출해서 리턴
		//return itemDao.allItem();
		return hibernateDao.allItem();
	}

	@Override
	@Transactional
	public Item getItem(HttpServletRequest request) {
		//파라미터 읽어오기
		String itemid = request.getParameter("itemid");
		//파라미터를 정수로 변환해서 Dao 메소드를 호출
		//return itemDao.getItem(Integer.parseInt(itemid));
		return hibernateDao.getItem(Integer.parseInt(itemid));
	}

	@Override
	@Transactional
	public Item getItem(HttpServletRequest request, int itemid) {
		//return itemDao.getItem(itemid);
		return hibernateDao.getItem(itemid);
	}

	@Override
	@Transactional
	public int insertItem(MultipartHttpServletRequest request) {
		//파라미터 읽기
		String itemid = request.getParameter("itemid");
		String itemname = request.getParameter("itemname");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		
		//Dao 객체의 파라미터 만들기
		Item item = new Item();
		item.setItemid(Integer.parseInt(itemid));
		item.setItemname(itemname);
		item.setPrice(Integer.parseInt(price));
		item.setDescription(description);
		
		//파일 읽기
		MultipartFile mf = request.getFile("pictureurl");
		//업로드할 파일이 있는 경우에만
		if(mf.isEmpty() == false) {
			//원본 파일이름 가져오기
			String originName = 
				request.getFile("pictureurl").getOriginalFilename();
			//원본 파일 이름은 여러 개의 파일을 업로드 하다보면 중복될 수 있기 때문에
			//파일 이름을 만들 때는 동일한 디렉토리에 저장한다면 중복 되지 않도록
			//파일 이름을 생성할 필요가 있습니다.
			//기본키와 파일명을 합치는 방법이 있고 UUID 클래스를 이용해서 만드는 방법
			String uploadName = itemid + originName;
			item.setPictureurl(uploadName);
			
			//파일을 저장할 경로를 생성
			//프로젝트의 내의 경로를 가지고 절대경로를 생성
			//프로젝트 내의 경로가 아니면 직접 경로를 작성
			String uploadPath = request.getRealPath("/img");
			//Servlet 3.0 이상인 경우는 
			//request.getServletContext().getRealPath("/img")
			
			//업로드할 File 객체 생성
			File file = new File(uploadPath + "\\" + uploadName);
			try {
				request.getFile("pictureurl").transferTo(file);
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//데이터 삽입
		return hibernateDao.insertItem(item);
	}

}










