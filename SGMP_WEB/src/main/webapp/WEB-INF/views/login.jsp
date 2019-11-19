<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="resources/css/login.css">
<head>
<meta charset="UTF-8">
<title>BSM</title>
</head>
<body>
	<div id="LoginMainBox">
		<div id="LoginSubTitle">
		THE BSM PROJECT
		</div>
		<div id="LoginMainTitle">
		BSM
		</div>
		<form id="login" action="/web/loginCheck" method="post">
			<table>
				<tr>
					<td><label>아이디</label></td>
					<td><input type="text" name="user_id" tabindex="2"></td>
					<td rowspan="2"><input type="submit" id="login_btn" value="로그인">
				</tr>
				<tr>
					<td><label>비밀번호</label></td>
					<td><input type="password" name="user_pw" tabindex="3"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>