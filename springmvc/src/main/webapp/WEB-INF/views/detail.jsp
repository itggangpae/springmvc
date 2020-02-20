<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${item.itemname}</title>
<link rel="stylesheet" href="../css/item.css">

</head>
<body>
	<div align="center">
		<h2>상품 상세보기</h2>
		<table border="1">
			<tr>
				<td><img src="../img/${item.pictureurl}" /></td>
				<td>
					<table>
						<tr height='50'>
							<td width='80'>상품명</td>
							<td width='160'>${item.itemname}</td>
						</tr>
						<tr height='50'>
							<td width='80'>가격</td>
							<td width='160'>${item.price}원</td>
						</tr>
						<tr height='50'>
							<td width='80'>효능</td>
							<td width='160'>${item.description}</td>
						</tr>
						<tr height='50'>
							<td colspan='2' align='center'>
								<a href='../'>■ 목록보기</a>
							</td>	
						</tr>						
					</table>
				</td>
			</tr>		
		</table>
	</div>
</body>
</html>






