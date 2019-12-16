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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css" />" />
<title>BSM</title>

<script>
	function click_company_insert() {
		window
				.open(
						"company_insert_page",
						'window',
						'location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=350,height=350,left=0, top=0, scrollbars=yes');
		return false
	}

	function click_company_id(COMPANY_ID) {
		var sal = COMPANY_ID;
		var link = "company_modify_info?COMPANY_ID=" + sal;
		window
				.open(
						link,
						'window',
						'location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=350,height=350,left=0, top=0, scrollbars=yes');
		return false

	}
</script>
</head>
<body>
<jsp:include page="header/header.jsp"></jsp:include>
<div style="padding-top:80px;">
	<div style="background-color:#464646;text-align:right;">
		<a class="btn btn-dark" data-toggle="collapse" href="#company_search_1" aria-expanded="false" aria-controls="company_search_1">검색</a>
		<a class="btn btn-dark" data-toggle="collapse" href="#" aria-expanded="false" onclick="javascript:click_company_insert();">회사등록</a>
	</div>
	<!-- 검색 -->
	<div class="collapse" id="company_search_1">
		<form id=company_search method="POST" action="company_search">
			<div class="input-group">
				<input type="text" id="search_text" name="search_text" class="form-control">
				<input type="submit" value="검색" class="btn btn-secondary">
			</div>
		</form>
	</div>
	<!-- 엑셀 -->
	<div class="collapse" id="excelForm_1">
		<form id="excelForm" name="excelForm" method="post" action="ExcelPoi">
			<div class="input-group">
				<input type="text" id="fileName" name="fileName" /> 
				<input type="submit" value="xls파일로 받기" class="btn btn-secondary"/>
			</div>
		</form>
	</div>
	<!-- 테이블 -->
		<div class="mx-auto" style="background-color:white;">
			<table class="table table-hover responsive-table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">회사ID
					<c:forEach var="item" items="${list}">
						<tr onclick="javascript:click_company_id('${item.user_id}'); ">
							<td>${item.user_id}
					</c:forEach>
				</thead>
			</table>
		</div>
</div>
</body>
</html>