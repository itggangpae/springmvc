package com.pk.db.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.pk.db.domain.Item;

public class ExcelView extends AbstractXlsView {

	//엑셀 데이터 출력을 위한 View
	@Override
	//첫번째 매개변수는 Controller가 Model 에 저장한 데이터를 가진 Map
	//두번째 매개변수는 실제 출력할 파일
	//세번째 와 네번째는 request 와 response 객체
	protected void buildExcelDocument(
			Map<String, Object> model, 
			Workbook workbook, 
			HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//데이터 찾아오기 - Controller에서 저장한 이름으로 찾아옵니다.
		List<Item> list = (List<Item>)model.get("list");
		
		//시트 만들기
		Sheet sheet = workbook.createSheet();
		workbook.setSheetName(0, "item");
		//각 열의 너비 설정
		sheet.setColumnWidth(0, 5120);
	
		
		//첫번째 행에 제목 출력
		Row firstRow = sheet.createRow(0);
		Cell cell = firstRow.createCell(0);
		cell.setCellValue("아이디");
		cell = firstRow.createCell(1);
		cell.setCellValue("이름");
		cell = firstRow.createCell(2);
		cell.setCellValue("가격");
		cell = firstRow.createCell(3);
		cell.setCellValue("효과");

		//데이터 출력
		int idx = 1;
		for(Item item : list) {
			Row row = sheet.createRow(idx);
			cell = row.createCell(0);
			cell.setCellValue(item.getItemid());
			cell = row.createCell(1);
			cell.setCellValue(item.getItemname());
			cell = row.createCell(2);
			cell.setCellValue(item.getPrice());
			cell = row.createCell(3);
			cell.setCellValue(item.getDescription());
			
			idx = idx + 1;
		}
		
	}

}




