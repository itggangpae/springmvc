<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅</title>
<style>
	#chatArea{
		width:200px;
		height:100px;
		overflow-y:auto;
		border:1px solid black;
	}
</style>
</head>
<body>
	이름<input type="text" id="nickname"/>
	<input type="button" id="enterbtn" value="입장"/>
	<input type="button" id="exitbtn" value="나가기"/>
	
	<h1>채팅 창</h1>
	<div id="chatArea">
		<div id="chatmessagearea"></div>
	</div>
	<br />
	
	<input type="text" id="message"/>
	<input type="button" id="sendbtn" value="전송"/>
</body>
</html>









