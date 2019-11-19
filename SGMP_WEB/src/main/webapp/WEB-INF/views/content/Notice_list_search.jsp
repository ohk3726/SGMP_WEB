<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
	<table width="700" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>이름</td>
			<td>날짜</td>
			<td>조회수</td>
		</tr>
		<c:forEach items="${list}" var="notice_list">
			<tr>
				<td>${notice_list.no_rownum}</td>

				<td><c:forEach begin="1" end="${notice_list.no_indent}">-</c:forEach> <a
					href="Notice_list_info?no_bid=${notice_list.no_bid}">${notice_list.no_title}</a></td>
				<td>${notice_list.no_name}</td>
				<td>${notice_list.no_date}</td>
				<td>${notice_list.no_hit}</td>

			</tr>

		</c:forEach>
		<a href="Notice_write">글작성</a>
		<c:if test="${id=='admin'}">
			<a href="Notice_write">글작성</a>
		</c:if>
		
		
	</form>
	</table>
	<buttom>
	<form action="Notice_Search" method="post">
		<select name="column" style="width: 140px; " >
			<option value="Notice_Title" selected="selected">글제목</option>
		</select>
		<input type="hidden" name="page" value="1">
		<input type="text" name="product_search_word" id="product_search_word" value="${product_search_word}" style="width:300px;">
		<input type="submit" name="product_search" value="검색">
		<a href="Notice_list?page=1">목록으로</a>
		</form>
		</buttom>
	
		
		
		<input type="hidden" name="product_search_word" style="width:300px;">
		
		<table border=1
			style="margin-left: auto; margin-right: auto; text-align: center;">
			<tr>
				<c:if test="${viewpage > 1 }">
					<td>
						<form action="Notice_Search" method="post">
							<input type="hidden" name="page" value="${viewpage-1 }">
							<input type="submit" value="이전">
						</form>
					</td>
				</c:if>
				<c:forEach var="i" begin="1" end="${pagenum}">
					<c:if test="${viewpage == i }">
						<td><input type="button" value="${i }"></td>
					</c:if>
					<c:if test="${viewpage != i }">
						<td>
							<form action="Notice_Search" method="post">
								<input type="hidden" name="page" value="${i}"> 
								<input type="hidden" name="product_search_word" value="${product_search_word}">
								<input type="submit" value="${i}">
							</form>
						</td>
					</c:if>
				</c:forEach>
				<c:if test="${viewpage < pagenum }">
					<td>
						<form action="Notice_Search" method="post">
							<input type="hidden" name="page" value="${viewpage+1 }">
							<input type="submit" value="다음">
						</form>
					</td>
				</c:if>
		</table>
		
		
		
</body>
</html>