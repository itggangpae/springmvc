package com.pk.db.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.pk.db.domain.Item;

public class PdfView extends AbstractPdfView {

	//pdf 출력을 위한 메소드
	//model은 Controller가 넘겨준 데이터
	//document는 출력한 문서 파일
	//writer는 직접 그려서 출력하고자 할 때 사용하는 객체
	//request 와 response는 이전과 동일
	@Override
	protected void buildPdfDocument(
			Map<String, Object> model, 
			Document document, 
			PdfWriter writer,
			HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		//데이터 찾아오기
		List<Item>list = (List<Item>)model.get("list");
		//한글 출력을 하고자 하는 경우에는 한글을 지원하는 폰트를 설정
		BaseFont dfKorean = 
			BaseFont.createFont("c:\\windows\\fonts\\malgun.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(dfKorean);
		
		//테이블을 생성 - 첫번째 매개변수는 열의 개수, 두번째 행의 개수
		Table table = new Table(4, list.size() + 1);
		table.setPadding(10);
		//제목 만들기
		Cell cell = new Cell(new Paragraph("코드", font));
		cell.setHeader(true);
		table.addCell(cell);
		cell = new Cell(new Paragraph("이름", font));
		cell.setHeader(true);
		table.addCell(cell);
		cell = new Cell(new Paragraph("가격", font));
		cell.setHeader(true);
		table.addCell(cell);
		cell = new Cell(new Paragraph("설명", font));
		cell.setHeader(true);
		table.addCell(cell);
		table.endHeaders();
		
		//데이터 출력하기
		for(Item item : list) {
			Cell imsi = new Cell(
				new Paragraph(item.getItemid()+"", font));
			table.addCell(imsi);
			imsi = new Cell(
				new Paragraph(item.getItemname(), font));
			table.addCell(imsi);
			imsi = new Cell(
				new Paragraph(item.getPrice()+"", font));
			table.addCell(imsi);
			imsi = new Cell(
				new Paragraph(item.getDescription(), font));
			table.addCell(imsi);
		}
		//테이블을 문서에 추가
		document.add(table);
		
		String path = request.getRealPath("/img");
		
		Image image = Image.getInstance(path + "/grape.jpg");
		image.scaleToFit(1000, 600);
		image.setAbsolutePosition(100, 100);
		document.add(image);

	}

}






