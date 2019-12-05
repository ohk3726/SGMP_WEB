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
<title>야구 용품 관리(본사용)</title>
</head>

<body id="home">
	<form id="product_info_insert" action="product_info_insert" method="post">
		<table class="table">

			<tr>
				<th>제품ID : <input type="text" id="product_ID" name="product_ID"></th>
			</tr>
			<tr>
				<th>제품이름 : <input type="text" id="product_NAME"
					name="product_NAME"></th>
			</tr>
			<tr>
				<th>제품수량 : <input type="number" id="product_CNT"
					name="product_CNT"></th>
			</tr>
			<tr>
				<th>입고가 : <input type="number" id="product_WEARING_PRICE"
					name="product_WEARING_PRICE"></th>
			</tr>
			<tr>
				<th>판매가 : <input type="number" id="product_PRICE"
					name="product_PRICE"></th>
			</tr>
			<tr>
				<th>지점명 : <input type="text" id="company_id" name="company_id"></th>
			</tr>
			<tr>
				<th>대분류 : <input type="text" id="prod_main_category"
					name="prod_main_category"></th>
			</tr>
			<tr>
				<th>중분류 : <input type="text" id="prod_sub_category"
					name="prod_sub_category"></th>
			</tr>
			<tr>
				<th>소분류 : <input type="text" id="prod_ssub_category"
					name="prod_ssub_category"></th>
			</tr>
			<tr>
				<th>제품최소 수량 : <input type="number" id="prod_cnt_min"
					name="prod_cnt_min"></th>
			</tr>


		</table>
		<input type="button" id="btn_insert" name="btn_insert" value="저장하기" onclick="javascript:changeSelect()" >
	</form>
</body>



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
		var company_id = document.getElementById('company_id').value;
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
		if (!company_id) {
			alert('회사의 ID를 입력하세요');
			document.getElementById('PRODUCT_PRICE').focus();
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



</html>