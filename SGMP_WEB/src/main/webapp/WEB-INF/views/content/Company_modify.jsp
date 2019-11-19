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
<title>Insert title here</title>

</head>
<body>
	<form action="company_info_modify" method="post">
		<table class="table">
			<c:forEach var="company_info" items="${company_info}">
				<tr>
					<th> 회사ID : <input type="text" id="company_id" name = "company_id"  value="${company_info.COMPANY_ID}" readonly></th>
				</tr>
				<tr>
					<th> 회사 이름 : <input type="text" id="company_name" name = "company_name" value="${company_info.COMPANY_NAME}"></th>
				</tr>
				<tr>
					<th> 회사 번호 : <input type="number" id="company_number" name = "company_number" value="${company_info.COMPANY_NUMBER}"></th>
				</tr>
				<tr>
					<th> 회사 주소 : <input type="number" id="company_address" name = "company_address" value="${company_info.COMPANY_ADDRESS}"></th>
				</tr>
			</c:forEach>
		</table>
		<input type="submit" id="btn_modify" name="btn_modify" value="수정하기">
	</form>
</body>
</html>