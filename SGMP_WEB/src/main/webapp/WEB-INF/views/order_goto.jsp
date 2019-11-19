<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BSM</title>
</head>
<body>
<form action="order_p2p_goto" method="post">
	<div>
		<c:forEach items="${list}" var="list">
			주문ID : ${list.prod_wearing_id}<input type="hidden" name="prod_wearing_id" value="${list.prod_wearing_id}"><br>
			제품명 : ${list.prod_name}<br>
			주문수량 : ${list.prod_wearing_cnt}<br>
			주문지점 : ${list.prod_wearing_company_id}<br>
		</c:forEach>	
	</div>
	<div>
		
			<table border=1>
				<thead>
					<tr>
						<td>선택
						<td>지점명
						<td>제품수량
				</thead>
				<tbody>
				<c:forEach items="${list1}" var="list1">
					<tr>
						<td><input type="radio" name="company_id" value="${list1.company_id}">
						<td>${list1.company_id}
						<td>${list1.prod_cnt}
					
				</c:forEach>
				</tbody>
			</table>
			<input type="submit" value="지점간이동">
	</div>
	</form>
</body>
</html>