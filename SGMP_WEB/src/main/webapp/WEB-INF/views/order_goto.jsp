<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<title>BSM</title>
</head>
<body>
<form action="order_p2p_goto" method="post">
	<div class="mx-auto" style="background-color:white;">
		<table class="table table-hover responsive-table">
			<thead class="thead-dark">
				<c:forEach items="${list}" var="list">
					<tr class="text-center">
						<th scope="col">주문ID<td>${list.prod_wearing_id}<input type="hidden" name="prod_wearing_id" value="${list.prod_wearing_id}">
					<tr class="text-center">
						<th scope="col">제품명<td>${list.prod_name}
					<tr class="text-center">
						<th scope="col">주문수량<td>${list.prod_wearing_cnt}
					<tr class="text-center">
						<th scope="col">주문지점<td>${list.prod_wearing_company_id}
				</c:forEach>	
				<tr class="text-center">
					<th>선택
					<th>지점명
			</thead>
			<tbody>
				<c:forEach items="${list1}" var="list1">
					<tr>
						<td><input type="radio" name="company_id" value="${list1.company_id}">
						<td>${list1.company_id}
				</c:forEach>
			</tbody>
		</table>	
	</div>
	<input type="submit" value="지점간이동" class="btn btn-secondary">
</form>
</body>
</html>