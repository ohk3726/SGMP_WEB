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
	<script>
				function click_close(){
					close();
				}
				
				function inNumber(){
					if(event.keyCode<48|| event.keyCode>57){
						event.returnValue=false;
					}
				}
				
			</script>
<title>야구 용품 관리(본사용)</title>

</head>

<body id="home">
<form  action="product_modify" method="post">
			<table class="table">
			<c:forEach var="productInfo" items="${productInfo}">
				<tr>
					<th> 제품ID : <input type="text" id="product_ID" name = "product_ID" value="${productInfo.prod_id}" readonly></th>
				</tr>
				<tr>
					<th> 제품이름 : <input type="text" id="product_NAME" name = "product_NAME" value="${productInfo.prod_name}"></th>
				</tr>
				<tr>
					<th> 제품수량 : <input type="text" id="product_CNT" name = "product_CNT" value="${productInfo.prod_cnt}"></th>
				</tr>
				<tr>
					<th> 제품단가 : <input type="text" id="product_ALL" name = "product_ALL" value="${productInfo.prod_all}" readonly></th>
				</tr>
				<tr>
					<th> 입고가 : <input type="text" id="product_WEARING_PRICE" name = "product_WEARING_PRICE" value="${productInfo.prod_wearing_price}"></th>
				</tr>
				<tr>
					<th> 판매가 : <input type="text" id="product_PRICE" name = "product_PRICE" onkeypress="inNumber();" value="${productInfo.prod_price}"></th>
				</tr>
				<tr>
					<th> 마진 : <input type="text" id="product_MARGIN" name = "product_MARGIN" value="${productInfo.prod_margin}" readonly></th>
				</tr>
				<tr>
					<th> 지점명 : <input type="text" id="company_id" name = "company_id" value="${productInfo.company_id}"></th>
				</tr>
				</c:forEach>
			</table>
			<input type="submit" id="btn_insert" name="btn_insert" onclick="javascript:click_close();" value="수정하기">
			</form >
			</body>
			
			
</html>