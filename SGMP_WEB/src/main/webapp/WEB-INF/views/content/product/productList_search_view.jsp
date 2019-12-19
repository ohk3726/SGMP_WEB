<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="resources/js/jquery.form.js"></script>
<script>
	//윈도우 방식으로 열지 않고 값을 넘겨야함  윈도우 로케이션 a태그로 사용

	function click_productid(product_id,company_id) {
		var sal = product_id;
		var com = company_id;
		var link = "productInfo?product_id=" + sal+"&company_id="+com;
		window
				.open(
						link,
						'window',
						'location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=600,height=600,left=0, top=0, scrollbars=yes');
		return false
	}

	function option_selected() {
		var option_1 = document.getElementById("option_id");

	}

	function click_product_insert() {
		window
				.open(
						"product_insert",
						'window',
						'location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=600,height=650,left=0, top=0, scrollbars=yes');
		return false
	}

	function change_search() {

		var search_option = document.getElementById("select_search_option");
		document.getElementById("search_option").value = search_option.options[search_option.selectedIndex].value;
		var sel = document.getElementById("selectCompany1");
		document.getElementById("selectCompany").value = sel.options[sel.selectedIndex].value;
		document.getElementById("select_search").submit();
	}

	function checkFileType(filePath) {
		var fileFormat = filePath.split(".");
		if (fileFormat.indexOf("xls") > -1) {
			return true;
		} else if (fileFormat.indexOf("xlsx") > -1) {
			return true;
		} else {
			return false;
		}
	}

	function check() {
		var file = $("#excel").val();
		if (file == "" || file == null) {
			alert("파일을 선택");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일만 업로드");
			return false;
		}
		var fileFormat = file.split(".");
		var fileType = fileFormat[1];
		if (confirm("업로드 하시겠습니까?")) {
			 $("#excelUpForm").attr("action", "compExcelUpload"); 
			var options = {
				type : "POST",
				processData: false,
				contentType: false,
				dataType:"text",
				data : {
					"excelType" : fileType
				},
				success : function(result) {
					alert(result);
					window.location.reload();;
				}
			};
			$("#excelUpForm").ajaxSubmit(options);
		}
	}
	function check_2() {
		var file = $("#excel_2").val();
		if (file == "" || file == null) {
			alert("파일을 선택");
			return false;
		} else if (!checkFileType(file)) {
			alert("엑셀 파일만 업로드");
			return false;
		}
		var fileFormat = file.split(".");
		var fileType = fileFormat[1];
		if (confirm("업로드 하시겠습니까?")) {
			var options = {
				type : "POST",
				processData: false,
				contentType: false,
				dataType:"text",
				data : {
					"excelType" : fileType
				},
				success : function(data) {
					if(data==""){
						alert("입력이 완료되었습니다.");
					}else {
						alert("아이디("+data+")가 존제하지 않습니다. 제품등록 후 다시 요청해주세요");
					}
				},
				error:function(request,status,error){
					alert("code:"+request.status+"message:"+request.responseText+"error : "+error);
				}
			};
			$("#excelUpdateForm").ajaxSubmit(options);
		}
	}
</script>
<div style="padding-top:80px;">
	<!-- 버튼 -->
	<div style="background-color:#464646;text-align:right;">
		<c:if test="${user_id=='admin'}"><a class="btn btn-dark" data-toggle="collapse" href="" aria-expanded="false" aria-controls="" id="btn_product_insert" onclick="javascript:click_product_insert();">제품등록</a></c:if>
		<c:if test="${user_id=='admin'}"><a class="btn btn-dark" data-toggle="collapse" href="#excel_insert" aria-expanded="false" aria-controls="excel_insert">대량제품등록</a></c:if>
		<c:if test="${user_id=='admin'}"><a class="btn btn-dark" data-toggle="collapse" href="#excel_update" aria-expanded="false" aria-controls="excel_update">본사재고입고</a></c:if>
		<a class="btn btn-dark" data-toggle="collapse" href="#search_page" aria-expanded="false" aria-controls="search_page">검색</a>
	</div>
	
	<!-- 제품 엑셀파일로 추가하기 -->
	<div class="collapse" id="excel_insert">	
		<form id="excelUpForm" method="post" action="compExcelUpload" role="form" enctype="multipart/form-data">
			<div class="input-group">
				<button type="button" class="btn btn-default">엑셀업로드 (업로드하고 디비에 INSERT)
					<input id="excel" name="excel" class="file custom-file-input" type="file" multiple data-show-upload="false" data-show-caption="true">
				</button>
				<button type="button" id="excelUp" onclick="check()" class="btn btn-secondary">등록</button>
			</div>
		</form>
	</div>
	
	<!-- 제품 엑셀파일로 수정하기-->
	<div class="collapse" id="excel_update">
		<form id="excelUpdateForm" method="post" action="compExceUpdate" role="form_2" enctype="multipart/form-data">
			<div class="input-group">
				<button type="button" class="btn btn-default">엑셀업로드 (DataBase Update)
					<input id="excel_2" name="excel_2" class="file custom-file-input" type="file" multiple data-show-upload="false" data-show-caption="true">
				</button> 
				<button type="button" id="excelUp_2" onclick="check_2()" class="btn btn-secondary">등록</button>
			</div>
		</form>
	</div>
	<!-- 검색 -->
	<div class="collapse" id="search_page">
		<form id=select_search method="POST" action="select_search">
		<div class="input-group">
			<c:if test="${user_id=='admin'}">
				<select name="selectCompany1" id="selectCompany1" onchange="javascript:changeSelect()"  class="custom-select">
					<option value="all" selected="selected">지점 선택하기</option>
					<c:forEach var="list_3" items="${list_3}">
						<option value="${list_3.company_id}" id="option_id" <c:if test="${sort==list_3.company_id}"> selected </c:if>> ${list_3.company_id}</option>
					</c:forEach>
				</select> 
				<input type="hidden" id="selectCompany" name="selectCompany">
			</c:if>
			<select name="select_search_option" id="select_search_option"  class="custom-select">
				<option value="option_title" id="option_title" selected="selected">제품명</option>
				<option value="option_id" id="option_id">제품ID</option>
			</select> 
			<input type="text" id="search_text" name="search_text" value="${search_text}" onkeypress="if(event.keyCode==13){javascript:change_search();}" class="form-control"> 
			<input type="button" value="검색" onclick="javascript:change_search();" class="btn btn-secondary"> 
			<input type="hidden" id="search_option" name="search_option">
			</div>
		</form>
	</div>
	
	<!-- 테이블 -->
	<div class="mx-auto" style="background-color:white;">
		<table class="table table-hover responsive-table">
			<thead class="thead-dark">		
				<tr class="text-center">
					<th>제품ID
					<th>제품명
					<th>재고수량
					<th>제품단가
					<th>입고가
					<th>판매가
					<th>마진
					<th>지점명
			</thead>
			<tbody>
				<c:forEach var="item" items="${list}">
					<tr onclick="javascript:click_productid('${item.prod_id}','${item.company_id}'); " class="text-center">
						<td>${item.prod_id}
						<td>${item.prod_name}<input type="hidden" id="product_id" name="product_id" value="${item.prod_id}">
						<td>${item.prod_cnt}
						<td>${item.prod_all}
						<td>${item.prod_wearing_price}
						<td>${item.prod_price}
						<td>${item.prod_margin}
						<td>${item.company_id}
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>