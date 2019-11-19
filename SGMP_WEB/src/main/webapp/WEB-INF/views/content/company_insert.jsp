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

</head>
<body>

<form  action="company_info_insert" method="post">
			<table class="table">
			
				<tr>
					<th> 회사ID : <input type="text" id="company_id" name = "company_id" ></th>
				</tr>
				<tr>
					<th> 회사 이름 : <input type="text" id="company_name" name = "company_name" ></th>
				</tr>
				<tr>
					<th> 회사 번호 : <input type="number" id="company_number" name = "company_number" ></th>
				</tr>
				<tr>
					<th> 회사 주소 : <input type="number" id="company_address" name = "company_address" ></th>
				</tr>
				
				
				
			</table>
			<input type="submit" id="btn_insert" name="btn_insert" value="저장하기">
			</form>
</body>
</html>