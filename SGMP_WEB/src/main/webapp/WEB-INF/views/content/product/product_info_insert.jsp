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
	<form id="product_info_insert" action="product_info_insert" method="post" style="padding-left:30px;padding-top:30px">
	<h1>제품상세</h1>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품ID</label>
				<div>
					<input type="text" id="product_ID" name = "product_ID"  class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품이름</label>
				<div>
					<input type="text" id="product_NAME" name = "product_NAME" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품수량</label>
				<div>
					<input type="text" id="product_CNT" name = "product_CNT" onkeypress="inNumber();" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">입고가</label>
				<div>
					<input type="text" id="product_WEARING_PRICE" name = "product_WEARING_PRICE" onkeypress="inNumber();" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">판매가</label>
				<div>
					<input type="text" id="product_PRICE" name = "product_PRICE" onkeypress="inNumber();" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">대분류</label>
				<div>
					<input type="text" id="prod_main_category" name = "prod_main_category" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">중분류</label>
				<div>
					<input type="text" id="prod_sub_category" name = "prod_sub_category" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">소분류</label>
				<div>
					<input type="text" id="prod_ssub_category" name = "prod_ssub_category" class="form-control">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-3 col-form-label">제품최소수량</label>
				<div>
					<input type="text" id="prod_cnt_min" name = "prod_cnt_min" class="form-control">
				</div>
			</div>
		<input type="button" id="btn_insert" name="btn_insert" value="저장하기" onclick="javascript:changeSelect()" class="btn btn-primary mb-2">
	</form>
<script>
	function changeSelect(){
		func();
		document.getElementById("product_info_insert").submit();
		
	}
	
	var func = function() {
		
		var product_ID = document.getElementById('product_ID').value;
		var product_NAME = document.getElementById('product_NAME').value;
		var product_CNT = document.getElementById('product_CNT').value;
		var product_WEARING_PRICE = document.getElementById('product_WEARING_PRICE').value;
		var product_PRICE = document
				.getElementById('product_PRICE').value;
		var prod_main_category = document
				.getElementById('prod_main_category').value;
		var prod_sub_category = document
				.getElementById('prod_sub_category').value;
		var prod_ssub_category = document
				.getElementById('prod_ssub_category').value;
		var prod_cnt_min = document
		.getElementById('prod_cnt_min').value;
		
		
		if (!product_ID) {
			alert('ID를 입력하세요');
			document.getElementById('PRODUCT_ID').focus();
			return product_info_insert;
		}
		if (!product_NAME) {
			alert('이름을 입력하세요');
			document.getElementById('PRODUCT_NAME').focus();
			return product_info_insert;
		}
		if (!product_CNT) {
			alert('사이즈을 입력하세요');
			document.getElementById('PRODUCT_SIZE').focus();
			return product_info_insert;
		}
		if (!product_WEARING_PRICE) {
			alert('원가 입력하세요');
			document.getElementById('PRODUCT_CNT').focus();
			return product_info_insert;
		}
		if (!product_PRICE) {
			alert('판매가 입력하세요');
			document.getElementById('PRODUCT_WEARING_PRICE').focus();
			return product_info_insert;
		}
		if (!prod_main_category) {
			alert('대분류를 입력하세요');
			document.getElementById('PRODUCT_MAIN_CATEGORY').focus();
			return product_info_insert;
		}

		if (!prod_sub_category) {
			alert('중분류을 입력하세요');
			document.getElementById('PRODUCT_SUB_CATEGORY').focus();
			return product_info_insert;
		}
		if (!prod_ssub_category) {
			alert('소분류을 입력하세요');
			document.getElementById('PRODUCT_SSUB_CATEGORY').focus();
			return product_info_insert;
		}
		if (!prod_cnt_min) {
			alert('최소수량을 입력하세요');
			document.getElementById('PRODUCT_SSUB_CATEGORY').focus();
			return product_info_insert;
		}

	}
</script>