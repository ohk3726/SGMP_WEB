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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/style.css" />" />
<title>Insert title here</title>
<script>
function changeSelect(){
	func();
	document.getElementById("company_info_insert").submit();
	
}



var func = function() {
	
	var company_id = document.getElementById('company_id').value;
	var company_pw = document.getElementById('company_pw').value;
	
	
	
	if (!company_id) {
		alert('ID를 입력하세요');
		document.getElementById('company_id').focus();
		return company_insert;
	}
	if (!company_pw) {
		alert('PW을 입력하세요');
		document.getElementById('company_pw').focus();
		return company_insert;
	}

}
</script>
</head>
<body>
	<form id="company_info_insert"action="company_info_insert" method="post">
		<div class="mx-auto" style="background-color:white;">
			<table class="table table-hover responsive-table">
				<thead class="thead-dark">
					<tr>
						<th scope="col">지점ID<td><input type="text" id="company_id" name="company_id" class="form-control">
					<tr>
						<th scope="col">지점PW<td><input type="text" id="company_pw" name="company_pw" class="form-control">
				</thead>
			</table>
		</div>
		<input type="button" id="btn_insert" class="btn btn-secondary" name="btn_insert" value="저장하기" onclick="javascript:changeSelect()">
	</form>
</body>
</html>