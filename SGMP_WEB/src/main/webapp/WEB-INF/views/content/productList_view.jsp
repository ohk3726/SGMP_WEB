<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="icon" type="image/png" href="resources/img/icons/favicon.ico"/>
<link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<link rel="stylesheet" type="text/css" href="resources/vendor/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet" type="text/css" href="resources/css/util.css">
<link rel="stylesheet" type="text/css" href="resources/css/main.css?ver=1">
<script>
	function changeSelect() {
		var sel = document.getElementById("selectCompany1");
		document.getElementById("selectCompany").value = sel.options[sel.selectedIndex].value;
		document.getElementById("PRO").submit();
	}
	

	function click_productid() {
		var sal = document.getElementById("product_id").value;
		var link = "productInfo?product_id="+sal;
		window.open(link,'window','location=no, directories=no,resizable=no,status=no,toolbar=no,menubar=no, width=350,height=350,left=0, top=0, scrollbars=yes');return false	
	}
	
	function option_selected(){
		var option_1 = document.getElementById("option_id");
		
	}
</script>

<div class="limiter">
	<form id="PRO" method="POST" action="PRO">
		<select name="selectCompany1" id="selectCompany1" onchange="javascript:changeSelect()">
			<option>정렬 선택하기</option>
			<c:forEach var="list_3" items="${list_3}">
				<option value="${list_3.company_id}" id ="option_id">${list_3.company_id}</option>
			</c:forEach>
		</select> 
		<input type="hidden" id="selectCompany" name="selectCompany">
	</form>
	<div class="container-table100">
		<div class="wrap-table100">
			<div class="table100 ver1 m-b-110">
				<div class="table100-head">
					<table>
						<thead>
							<tr class="row100 head">
								<th class="cell100 column1">제품ID
								<th class="cell100 column2" style="width:30%;">제품명
								<th class="cell100 column3" style="width:10%;">재고수량
								<th class="cell100 column4">제품단가
								<th class="cell100 column5">입고가
								<th class="cell100 column6">판매가
								<th class="cell100 column7">마진
								<th class="cell100 column8">지점명
							</tr>
						</thead>
					</table>
				</div>
				<div class="table100-body js-pscroll">
					<form id=productInfo method="POST" action="productInfo">
						<table>
							<tbody>
								<c:forEach var="item" items="${list}">
									<tr>
										<td class="cell100 column1">${item.prod_id}
										<td class="cell100 column2" style="width:30%;"><a href='#' id="product_a" value="${item.prod_id}" onclick="javascript:click_productid(); ">${item.prod_name}</a>
											<input type="hidden" id="product_id" name="product_id" value="${item.prod_id}">
										<td class="cell100 column3" style="width:10%;">${item.prod_cnt}
										<td class="cell100 column4">${item.prod_all}
										<td class="cell100 column5">${item.prod_wearing_price}
										<td class="cell100 column6">${item.prod_price}
										<td class="cell100 column7">${item.prod_margin}
										<td class="cell100 column8">${item.company_id}
								</c:forEach>
							</tbody>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>