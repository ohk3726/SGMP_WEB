<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<script>
	function search(){
		document.getElementById("search").submit();
	}
	function select(no_bid){
		location.href="Notice_list_info?no_bid="+no_bid;
	}
</script>
	<div style="padding-top:80px;">	
		<div class="mx-auto" style="background-color:white;height:100%;">
			<table class="table table-hover" >
				<thead class="thead-dark">
					<tr class="text-center">
						<th scope="col">번호</th>
						<th scope="col">제목</th>
						<th scope="col">이름</th>
						<th scope="col">날짜</th>
						<th scope="col">조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="notice_list">
						<tr class="text-center" onclick="javascript:select(${notice_list.no_bid});">
							<td>${notice_list.no_rownum}</td>
							<td><c:forEach begin="1" end="${notice_list.no_indent}">-</c:forEach>${notice_list.no_title}</td>
							<td>${notice_list.no_name}</td>
							<td>${notice_list.no_date}</td>
							<td>${notice_list.no_hit}</td>
						</tr>
					</c:forEach>
					<c:if test="${user_id=='admin'}">
						<div style="background-color:#464646;text-align:right;">					
							<a class="btn btn-dark" href="Notice_write">글작성</a>
						</div>
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
	