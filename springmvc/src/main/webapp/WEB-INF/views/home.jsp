<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- if 나 forEach를 사용하기 위한 태그 라이브러리를 설정 -->
<%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 출력</title>
<link rel="stylesheet" href="./css/item.css">
</head>
<body>
	<h2 align='center'>상품 목록</h2>
	<table border='1' align='center'>
		<tr class='header'>
			<th width='80'>상품ID</th>
			<th width='160'>상품이름</th>
			<th width='80'>가격</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr class="record">
				<td align='center'>${item.itemid}</td>
				<td>&nbsp; &nbsp; ${item.itemname}</td>
				<td align='right'>${item.price}원&nbsp; &nbsp;</td>
			</tr>
		</c:forEach>	
	</table>
</body>
</html>




