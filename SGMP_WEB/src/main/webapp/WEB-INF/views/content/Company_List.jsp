<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta name="MSSmartTagsPreventParsing" content="TRUE" />
<meta http-equiv="expires" content="-1" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="Tom Cryns" />
<meta name="description" content="webtom grey website template" />
<title>Insert title here</title>

<script>
function click_company_insert(){
	window.open("company_insert_page",'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=350,height=350,left=0, top=0, scrollbars=yes');return false
}


function click_company_id(COMPANY_ID){
	var sal = COMPANY_ID;
	var link = "company_modify_info?COMPANY_ID=" + sal;
	window.open(link,'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=350,height=350,left=0, top=0, scrollbars=yes');return false

}

</script>
</head>
<body>
<div id="container">
	<div id="content">
		<form id=company_search method="POST" action="company_search">
			<p>회사이름 검색: 
				<input type="text" id="search_text" name="search_text"> 
				<input type="submit" value ="검색">
			</p>		
		</form>
		<button type="button" id="btn_company_insert" onclick="javascript:click_company_insert(); ">회사등록</button>
		<table class="table">
			<tr>
				<th>회사ID
				<th>회사이름
				<th>회사 주소
				<th>회사 번호
			<c:forEach var="item" items="${list}">
				<tr>
					<td>${item.COMPANY_ID}
					<td>
						<a href='#' id="company_id" onclick="javascript:click_company_id('${item.COMPANY_ID}'); ">${item.COMPANY_NAME}</a>
						<input type="hidden" id="company_id_hidden" name="company_id_hidden" value="${item.COMPANY_ID}">
					<td>${item.COMPANY_ADDRESS}
					<td>${item.COMPANY_NUMBER}
			</c:forEach>
		</table>
	</div>
</body>
</html>