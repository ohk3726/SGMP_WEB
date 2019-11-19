<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!-- 스타일 -->
<link rel="icon" type="image/png" href="resources/img/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css?ver=1">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<!-- 검색버튼 스크립트 -->
<script>
	function condition_change(){
		var condition_changed = document.getElementById("prod_wearing_condition");
		document.getElementById("change_condition").value = condition_changed.options[condition_changed.selectedIndex].value;
		document.getElementById("condition_change").submit();
	}
	function search_1(){
		var sel_condition = document.getElementById("selectCondition1");
		document.getElementById("selectCondition").value = sel_condition.options[sel_condition.selectedIndex].value;
		var sel_company = document.getElementById("selectCompany1");
		document.getElementById("selectCompany").value = sel_company.options[sel_company.selectedIndex].value;
		var sel_search = document.getElementById("search_select");
		document.getElementById("searchSelect").value = sel_search.options[sel_search.selectedIndex].value;
		document.getElementById("search").submit();
	}
</script>	
<!-- 검색 -->
	<div style="float:left;width:100%;background-color:white;text-align:center;">
		<form action="search_p2p" method="post" id="search">
			<br>
			&nbsp&nbsp&nbsp
			날짜 
			<input type="date" name="date1" id="date1" style="display:inline-block;border:1px solid black;" value="${date1}">
			 ~ 
			<input type="date" name="date2" id="date2" style="display:inline-block;border:1px solid black;" value="${date2}">
			&nbsp&nbsp&nbsp
			<select name="selectCondition1" id="selectCondition1">
				<option value="처리상태선택하기">처리상태선택하기</option>
				<c:forEach var="list_3" items="${list_3}">
					<option value="${list_3.prod_wearing_condition}">${list_3.prod_wearing_condition}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="selectCondition" id="selectCondition">
			<select name="selectCompany1" id="selectCompany1">
				<option value="지점명선택하기">지점명선택하기</option>
				<c:forEach var="list_4" items="${list_4}">
					<option value="${list_4.prod_wearing_company_id}">${list_4.prod_wearing_company_id}</option>
				</c:forEach>
			</select>
			<input type="hidden" name="selectCompany" id="selectCompany">
			<select id="search_select">
				<option value="prod_name" selected="selected">제품명</option>
				<option value="prod_wearing_id">주문ID</option>
			</select>
			<input type="hidden" name="searchSelect" id="searchSelect">
			<input type="text" name="search_keyword" id="search_keyword" style="display:inline-block;width:300px;border:1px solid black;" onkeypress="if(event.keyCode==13){javascript:search_1();}">
			<input type="button" value="검색" style="display:inline-block;border:1px solid black;" onclick="javascript:search_1();">
			<br><br>
		</form>
	</div>
	<script>
		$("#selectCondition1 > option[value=${sel_condition}]").attr("selected",true);
	</script>
	<script>
		$("#selectCompany1 > option[value=${sel_company}]").attr("selected",true);
	</script>
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
<!-- 처리상태변경 -->
	<div style="float:left;width:100%;background-color:white;text-align:center;">
		<br>
		<select id="prod_wearing_condition">
			<option>변경 할 처리상태를 선택하세요.</option>
			<option value="출고완료">출고완료</option>
			<option value="배송중">배송중</option>
			<option value="배송완료">배송완료</option>
		</select>
		<input type="button" value="변경" style="display:inline-block;border:1px solid black;" onclick="javascript:condition_change();">
		<br><br>
	</div>
	
	
<!-- 테이블 -->
	<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
							<tr class="row100 head">
								<th class="cell100 column1">선택
								<th class="cell100 column2">처리상태
								<th class="cell100 column3">주문ID
								<th class="cell100 column4">제품명
								<th class="cell100 column5">단가
								<th class="cell100 column6">주문가격
								<th class="cell100 column7">주문수량
								<th class="cell100 column8">본사재고수량
								<th class="cell100 column9">지점명
							</thead>
						</table>
					</div>
					<div class="table100-body js-pscroll">
						<form action="condition_change" method="post" id="condition_change">
							<input type="hidden" name="change_condition" id="change_condition">
							<table>
								<tbody>
									<c:forEach items="${list}" var="order_list">
										<tr class="row100 body">
											<td class="cell100 column1" style="padding-left:80px;"><input type="checkbox" name="condition_check" value="${order_list.prod_wearing_id}"></td>
											<td class="cell100 column2"><input type="text" name="prod_wearing_condition" value="${order_list.prod_wearing_condition}" readonly></td>
											<td class="cell100 column3"><input type="text" name="prod_wearing_id" value="${order_list.prod_wearing_id}" readonly></td>
											<td class="cell100 column4"><input type="text" name="prod_name" value="${order_list.prod_name}" readonly></td>
											<td class="cell100 column5"><input type="text" name="prod_wearing_price" value="${order_list.prod_wearing_price}" readonly></td>
											<td class="cell100 column6"><input type="text" name="prod_wearing_price_calc" value="${order_list.prod_wearing_price_calc}" readonly></td>
											<td class="cell100 column7"><input type="text" name="prod_wearing_cnt" value="${order_list.prod_wearing_cnt}" readonly></td>
											<td class="cell100 column8"><input type="text" name="prod_root_cnt" value="${order_list.prod_root_cnt}" readonly></td>
											<td class="cell100 column9"><input type="text" name="prod_wearing_company_id" value="${order_list.prod_wearing_company_id}" readonly></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
<!-- 테이블 스크립트 -->
	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/vendor/select2/select2.min.js"></script>
	<script src="resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
	<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.min.js"></script>
<!-- 테이블 스크립트 -->
	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});	
	</script>
	<script src="resources/js/main.js"></script>