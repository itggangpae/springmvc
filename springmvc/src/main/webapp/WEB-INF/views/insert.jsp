<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>데이터 삽입</title>
</head>
<body>
	<div align='center'>
		<h3>데이터 삽입</h3>
		<form method="post" enctype="multipart/form-data"
		id="myform">
			<table border="1">
				<tr>
					<td rowspan="5" align="center">
						<p></p>
						<img id="img" width="100" height="100"
							border="1"/>
						<br/>
						<input type="file" id="pictureurl"
						name="pictureurl"
						accept=".jpg,.jpeg,.gif,.png" />
						<br />
					</td>
				</tr>
				
				<tr>
					<td align="right">코드</td>
					<td><input type="text" name="itemid"
					id="itemid" required="required" />
					<div id="iddiv"></div>
					</td>
				</tr>
				<tr>
					<td align="right">이름</td>
					<td><input type="text" name="itemname"
					id="itemname" required="required" />
					</td>
				</tr>
				<tr>
					<td align="right">가격</td>
					<td><input type="text" name="price"
					id="price" required="required" />
					</td>
				</tr>
				<tr>
					<td align="right">효과</td>
					<td><input type="text" name="description"
					id="description" required="required" />
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="전송" />
						<input type="button" value="메인" 
						id="mainbtn"/>
					</td>
				</tr>		
						
			</table>
		</form>
	</div>
</body>
</html>




