<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="MSSmartTagsPreventParsing" content="TRUE" /> 
<meta http-equiv="expires" content="-1" /> 
<meta http-equiv= "pragma" content="no-cache" /> 
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="Tom Cryns" /> 
<meta name="description" content="webtom grey website template" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>
<title>具备 侩前 包府(夯荤侩)</title>
</head>
<body id="home">
	<div id="container">
    	<div id="watermark">
        	
    	</div>
    	<div id="header">
    		<h1>具备 侩前 包府(夯荤侩)</h1>
    	</div>
    	<div id="sidebar">
        	<h1>皋春</h1>
        	<ul id="sidebarnav">
            	<li><a href="#">力前喊 荐樊 包府</a></li>
            	<li><a href="#">瘤痢喊 涝绊 包府</a></li>
            	<li><a href="#">瘤痢喊 免绊 包府</a></li>
            	<li><a href="#">瘤痢喊 惑前捞悼 包府</a></li>
        	</ul>
    	</div>
		<div id="content">
     		<h1>力前包府</h1>
     		<table class="table">
				<tr>
					<th>力前ID
					<th>力前捞抚
					<th>力前啊拜
					<th>力前荐樊
				<c:forEach var="item" items="${list}">
					<tr>
						<td>${item.prod_id}
						<td>${item.prod_name}
						<td>${item.prod_price}
						<td>${item.prod_cnt}
				</c:forEach>
			</table>
		</div>
	    <div id="ads">
	         <p>Space reserved for text advertisements</p>
	    </div>
	    <div id="footer">
	        <p><a href="#">Privacy</a> | <a href="#">Terms</a></p>
	        <p>
	        	Valid <a href="http://validator.w3.org/check?uri=referer">XHTML</a> | Valid <a href="http://jigsaw.w3.org/css-validator/check/referer">CSS</a>
	        </p>
	        <p>&copy; 2019 your copyphrase | template design by <a href="http://www.webtom.be">webtom.be</a></p>
	    </div>
	</div>

</body>
</html>