<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!-- 검색버튼 스크립트 -->
<script>
function condition_change(){
	var count = 0;
	var f1=document.getElementById("condition_change")
	var length=f1.condition_check.length;
	var i=0;
	
	if(length){
		while(i<length){
			if(f1.condition_check[i].checked){
				count += count+1;
			}
			i++;
		}
		
		if(count == 0){
			alert("목록을 체크해주세요");
			return false;
		}
	}
	
	var condition_changed = document.getElementById("prod_wearing_condition");
	document.getElementById("change_condition").value = condition_changed.options[condition_changed.selectedIndex].value;
	
	if(document.getElementById("change_condition").value == "no"){
		alert("변경 상태를 선택해주세요");
		return false;
	}
	
	document.getElementById("condition_change").submit();
}
	function search_1(){
		var sel_condition = document.getElementById("selectCondition1");
		document.getElementById("selectCondition").value = sel_condition.options[sel_condition.selectedIndex].value;
		<c:if test="${user_id=='admin'}">
			var sel_company = document.getElementById("selectCompany1");
			document.getElementById("selectCompany").value = sel_company.options[sel_company.selectedIndex].value;
		</c:if>
		var sel_search = document.getElementById("search_select");
		document.getElementById("searchSelect").value = sel_search.options[sel_search.selectedIndex].value;
		document.getElementById("search").submit();
	}
</script>	
<div style="padding-top:80px;">
<!-- 검색 -->
	<div style="background-color:#464646;text-align:right;">
		<c:if test="${user_id=='admin'}"><a class="btn btn-dark" data-toggle="collapse" href="#condition_change_page" aria-expanded="false" aria-controls="condition_change_page">처리상태변경</a></c:if>			
		<c:if test="${user_id!='admin'}"><a class="btn btn-dark" href="excel_list" aria-expanded="false">입고리스트출력</a></c:if>
		<c:if test="${user_id=='admin'}"><a class="btn btn-dark" href="excel_list" aria-expanded="false">출고리스트출력</a></c:if>	
		<a class="btn btn-dark" data-toggle="collapse" href="#search_page" aria-expanded="false" aria-controls="search_page">검색창</a>
	</div>
	<div class="collapse" id="search_page">
		<form action="search" method="post" id="search">
			<div class="input-group">
				<input type="text" class="form-control" value="날짜" readonly>
				<input type="date" name="date1" id="date1" class="form-control" value="${date1}">
				<input type="date" name="date2" id="date2" class="form-control" value="${date2}">
				<select name="selectCondition1" id="selectCondition1" class="custom-select">
					<option value="처리상태선택하기" selected="selected">처리상태선택하기</option>
					<c:forEach var="list_3" items="${list_3}">
						<option value="${list_3.prod_wearing_condition}" id ="option_id">${list_3.prod_wearing_condition}</option>
					</c:forEach>
				</select>
				<input type="hidden" name="selectCondition" id="selectCondition">
				<c:if test="${user_id=='admin'}">
				<select name="selectCompany1" id="selectCompany1" class="custom-select" >
					<option value="지점명선택하기" selected="selected">지점명선택하기</option>
					<c:forEach var="list_4" items="${list_4}">
						<option value="${list_4.prod_wearing_company_id}" id ="option_id">${list_4.prod_wearing_company_id}</option>
					</c:forEach>
				</select>
				</c:if>
				<input type="hidden" name="selectCompany" id="selectCompany">
				<select id="search_select" class="custom-select">
					<option value="prod_name" selected="selected">제품명</option>
					<option value="prod_wearing_id">주문ID</option>
				</select>
				<input type="text" name="search_keyword" id="search_keyword" class="form-control" style="width:300px;" value="${sel_search_keyword}"  onkeypress="if(event.keyCode==13){javascript:search_1();}">
				<input type="button" value="검색" class="btn btn-secondary" onclick="javascript:search_1();">
				<input type="hidden" name="searchSelect" id="searchSelect">
			</div>
		</form>
	</div>
<!-- 처리상태변경 -->
	<div style="float:left;width:100%;background-color:#464646;text-align:center;"  class="collapse" id="condition_change_page">
		<div class="input-group">
			<select id="prod_wearing_condition" class="form-control">
				<option value="no">변경 할 처리상태를 선택하세요.</option>
				<option value="출고완료">출고완료</option>
				<option value="배송중">배송중</option>
				<option value="배송완료">배송완료</option>
			</select>
			<input type="button" value="변경" class="btn btn-secondary" onclick="javascript:condition_change();">
		</div>
	</div>
	<script>
		$("#selectCondition1 > option[value=${sel_condition}]").attr("selected",true);
	</script>
	<c:if test="${user_id=='admin'}">
		<script>
			$("#selectCompany1 > option[value=${sel_company}]").attr("selected",true);
		</script>
	</c:if>
	<script>
		$("#search_select > option[value=${sel_search}]").attr("selected",true);
	</script>
	<script>
		document.getElementById("search_keyword").value = "${sel_search_keyword}";
	</script>
	<script>
		document.getElementById("date1").valueAsDate = ${date1};
		document.getElementById("date2").valueAsDate = ${date2};
	</script>
<!-- 테이블 -->
	<div class="mx-auto" style="background-color:white;">
		<table class="table table-hover responsive-table">
			<thead class="thead-dark">
				<tr class="text-center">
					<th scope="col">선택
					<th scope="col">처리상태
					<th scope="col">주문ID
					<th scope="col">제품명
					<th scope="col">단가
					<th scope="col">주문가격
					<th scope="col">주문수량
					<th scope="col">출고지점재고수량
					<th scope="col">출고지점
					<th scope="col">주문지점명
				</tr>
			</thead>
			<tbody>
				<form action="condition_change" method="post" id="condition_change">
					<input type="hidden" name="change_condition" id="change_condition">
						<c:forEach items="${list}" var="order_list">
							<tr class="text-center">
								<td><input type="checkbox" name="condition_check" value="${order_list.prod_wearing_id}"></td>
								<td>${order_list.prod_wearing_condition}<input type="hidden" name="prod_wearing_condition" value="${order_list.prod_wearing_condition}" readonly></td>
								<td>${order_list.prod_wearing_id}<input type="hidden" name="prod_wearing_id" value="${order_list.prod_wearing_id}" readonly></td>
								<td>${order_list.prod_name}<input type="hidden" name="prod_name" value="${order_list.prod_name}" readonly></td>
								<td>${order_list.prod_wearing_price}<input type="hidden" name="prod_wearing_price" value="${order_list.prod_wearing_price}" readonly></td>
								<td>${order_list.prod_wearing_price_calc}<input type="hidden" name="prod_wearing_price_calc" value="${order_list.prod_wearing_price_calc}" readonly></td>
								<td>${order_list.prod_wearing_cnt}<input type="hidden" name="prod_wearing_cnt" value="${order_list.prod_wearing_cnt}" readonly></td>
								<td>${order_list.prod_root_cnt}<input type="hidden" name="prod_root_cnt" value="${order_list.prod_root_cnt}" readonly></td>
								<td>${order_list.prod_wearing_release}<input type="hidden" name="prod_wearing_release" value="${order_list.prod_wearing_release}" readonly></td>
								<td>${order_list.prod_wearing_company_id}<input type="hidden" name="prod_wearing_company_id" value="${order_list.prod_wearing_company_id}" readonly></td>
							</tr>
						</c:forEach>
				</form>
			</tbody>
		</table>
	</div>
</div>