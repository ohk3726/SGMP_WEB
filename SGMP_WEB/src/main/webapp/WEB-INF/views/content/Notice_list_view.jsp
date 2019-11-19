<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
	function search(){
		document.getElementById("search").submit();
	}
	function select(no_bid){
		location.href="Notice_list_info?no_bid="+no_bid;
	}
</script>
	<div style="padding-top:10%;">	
		<div style="width:80%;border:1px solid black;" class="mx-auto">
			<table class="table table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">이름</th>
						<th scope="col">날짜</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="notice_list">
						<tr onclick="javascript:select(${notice_list.no_bid});">
							<td>${notice_list.no_rownum}</td>
							<td><c:forEach begin="1" end="${notice_list.no_indent}">-</c:forEach>${notice_list.no_title}</td>
							<td>${notice_list.no_name}</td>
							<td>${notice_list.no_date}</td>
							<td>${notice_list.no_hit}</td>
						</tr>
					</c:forEach>
					<c:if test="${id=='admin'}">
						<a href="Notice_write">글작성</a>
					</c:if>
				</tbody>
			</table>
			<br>
			<form action="Notice_Search" method="post" id="search">
				<div class="input-group mx-auto" style="width:80%;">
					<div class="input-group-prepend">
						<input type="hidden" name="column" value="Notice_Title">
				    	<button class="btn btn-outline-secondary dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">글제목</button>
					    <div class="dropdown-menu">
					     	<a class="dropdown-item" href="#">글제목</a>
					    </div>
				 	</div>
					<input type="hidden" name="page" value="1">
					<input type="text" class="form-control" name="product_search_word">
					<div>
						<button class="btn btn-outline-secondary" type="button" onclick="javascript:search();">검색</button>
					</div>
				</div>
			</form>
			<br>
			<ul class="pagination justify-content-center">
				<c:if test="${viewpage > 1 }">
					<li class="page-item"><a class="page-link" href="Notice_list?page=${viewpage-1 }">이전</a></li>
				</c:if>
				<c:forEach var="i" begin="1" end="${pagenum}">
					<c:if test="${viewpage != i }">
						<li class="page-item"><a class="page-link" href="Notice_list?page=${i }">${i }</a></li>
					</c:if>
					<c:if test="${viewpage == i }">
						<li class="page-item active">
					     	<a class="page-link" href="#">${i }<span class="sr-only">(current)</span></a>
					    </li>
					</c:if>
				</c:forEach>
				<c:if test="${viewpage < pagenum }">
					<li class="page-item"><a class="page-link" href="Notice_list?page=${viewpage+1 }">다음</a></li>
				</c:if>
			</ul>
			<br>
	</div>
</div>
	