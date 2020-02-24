<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<div align='center'>
	<div>${msg}</div>
	<form method="post" id="loginform">
		아이디<input type="text" id="userid" name="userid"
		required="required"/><br/>
		비밀번호<input type="text" id="userpw" name="userpw"
		required="required"/><br/>
		<input type="submit" value="전송"/>
	</form>
</div>
</body>
</html>
