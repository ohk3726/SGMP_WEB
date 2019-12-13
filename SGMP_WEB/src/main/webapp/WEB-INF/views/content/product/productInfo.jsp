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

<form action="product_modify" method="post" style="padding-left:30px;padding-top:30px">
	<h1>제품상세</h1>
		<c:forEach var="productInfo" items="${productInfo}">
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품ID</label>
				<div>
					<input type="text" id="product_ID" name = "product_ID" value="${productInfo.prod_id}" readonly  class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품이름</label>
				<div>
					<input type="text" id="product_NAME" name = "product_NAME" value="${productInfo.prod_name}" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품수량</label>
				<div>
					<input type="text" id="product_CNT" name = "product_CNT" value="${productInfo.prod_cnt}" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">재고총가격</label>
				<div>
					<input type="text" id="product_ALL" name = "product_ALL" value="${productInfo.prod_all}" readonly class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">입고가</label>
				<div>
					<input type="text" id="product_WEARING_PRICE" name = "product_WEARING_PRICE" value="${productInfo.prod_wearing_price}" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">판매가</label>
				<div>
					<input type="text" id="product_PRICE" name = "product_PRICE" onkeypress="inNumber();" value="${productInfo.prod_price}" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">마진</label>
				<div>
					<input type="text" id="product_MARGIN" name = "product_MARGIN" value="${productInfo.prod_margin}" readonly class="form-control-plaintext">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">지점명</label>
				<div>
					<input type="text" id="company_id" name = "company_id" value="${productInfo.company_id}" readonly class="form-control-plaintext">
				</div>
			</div>
		</c:forEach>
	<input type="submit" id="btn_insert" name="btn_insert" value="수정하기" class="btn btn-primary mb-2">
</form >
