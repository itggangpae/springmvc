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

<style>
	#display{
		height:300px;
		width:400px;
		overflow-y:auto;
		border:1px solid black;
	}
</style>

</head>
<body>
	<h2 align='center'>상품 목록</h2>
	<div id="display" align="center">
	<table border='1' align='center'>
		<tr class='header'>
			<th width='80'>상품ID</th>
			<th width='160'>상품이름</th>
			<th width='80'>가격</th>
		</tr>
		<c:forEach items="${list}" var="item">
			<tr class="record">
				<td align='center'>${item.itemid}</td>
				<td>&nbsp; &nbsp; 
				<a href="detail/${item.itemid}">${item.itemname}</a>
				</td>
				<td align='right'>${item.price}원&nbsp; &nbsp;</td>
			</tr>
		</c:forEach>	
	</table>
	</div>
	<c:if test="${member == null}">
		<a href="login">로그인</a><br/>
	</c:if>
	<c:if test="${member != null}">
		${member.nickname}님 <a href="logout">로그아웃</a><br/>
	</c:if>
	
	<div>
	<a href="insert">데이터 삽입</a><br/>
	<a href="chat">WebSocket을 이용한 채팅 구현</a><br/>
	<a href="item.xls">엑셀로 내려받기</a><br />
	<a href="item.pdf">pdf로 내려받기</a><br />
	<a href="item1.json">json으로 내려받기</a><br />
	<a href="item.csv">csv로 내려받기</a><br />
	<a href="item2.json">json로 내려받기</a><br />
	<a href="#" id="ajax">ajax로 json 데이터 사용하기</a><br />
	<a href="item.xml">XML로 내려받기</a><br/>
	</div>
	<div align='center' id="disp"></div>
</body>

<!-- JavaScript: html 내에서 동적으로 변화를 주기 위한 언어 -->
<script>
	document.getElementById("ajax").addEventListener(
			"click", function(){
		//ajax 객체 만들기
		request = new XMLHttpRequest();
		//요청 생성
		request.open('GET', 'item2.json');
		//요청 전송
		request.send('');
		//요청에 대한 처리를 위한 콜백 메소드 등록
		request.onreadystatechange = function(){
			//정상 응답이 오면
			if(request.readyState == 4 && 
				request.status >= 200 && request.status < 300){
				//받아온 데이터를 JSON 파싱을 해서 배열로 만들기
				list = JSON.parse(request.responseText);
				//배열을 순회
				msg = '';
				msg += "<table border='1'>";
				msg += "<tr class='header'>";
				msg += "<th width='80'>코드</th>";
				msg += "<th width='160'>상품명</th>";
				msg += "<th width='80'>가격</th>";
				msg += "</tr>";
				for(i in list){
					msg += "<tr class='record'>";
					msg += "<td align='center'>" + list[i].itemid + "</td>";
					msg += "<td align='left'>" + list[i].itemname + "</td>";
					msg += "<td align='right'>" + list[i].price + "</td>";
					msg += "</tr>";
				}
				msg += "</table>";
				document.getElementById("disp").innerHTML = msg;
			}
		}
	});
</script>

</html>












