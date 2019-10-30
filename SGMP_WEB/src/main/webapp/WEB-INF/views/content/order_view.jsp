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
								<th class="cell100 column3">제품명
								<th class="cell100 column4">단가
								<th class="cell100 column5">주문가격
								<th class="cell100 column6">주문수량
								<th class="cell100 column7">본사재고수량
								<th class="cell100 column8">지점명
							</thead>
						</table>
					</div>
					<div class="table100-body js-pscroll">
						<table>
							<tbody>
								<c:forEach items="${list}" var="order_list">
									<tr class="row100 body">
										<td class="cell100 column1" style="padding-left:80px;"><input type="checkbox" name="condition_check"></td>
										<td class="cell100 column2"><input type="text" name="prod_wearing_condition" value="${order_list.prod_wearing_condition}" readonly></td>
										<td class="cell100 column3"><input type="text" name="prod_name" value="${order_list.prod_name}" readonly></td>
										<td class="cell100 column4"><input type="text" name="prod_wearing_price" value="${order_list.prod_wearing_price}" readonly></td>
										<td class="cell100 column5"><input type="text" name="prod_wearing_price_calc" value="${order_list.prod_wearing_price_calc}" readonly></td>
										<td class="cell100 column6"><input type="text" name="prod_wearing_cnt" value="${order_list.prod_wearing_cnt}" readonly></td>
										<td class="cell100 column7"><input type="text" name="prod_root_cnt" value="${order_list.prod_root_cnt}" readonly></td>
										<td class="cell100 column8"><input type="text" name="prod_wearing_company_id" value="${order_list.prod_wearing_company_id}" readonly></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/vendor/jquery/jquery-3.2.1.min.js"></script>
	<script src="resources/vendor/bootstrap/js/popper.js"></script>
	<script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="resources/vendor/select2/select2.min.js"></script>
	<script src="resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});
			
		
	</script>
	<script src="resources/js/main.js"></script>