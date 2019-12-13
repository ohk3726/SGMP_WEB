<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="resources/css/style.css">
<nav class="navbar navbar-dark navbar-expand-lg fixed-top" style="background-color:#464646;font-size:30px;height:80px;">
	<!-- 홈 -->
	<a class="navbar-brand" href="/web/main">BSM</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    	<span class="navbar-toggler-icon"></span>
  	</button>
	<!-- 메뉴 -->
	<div class="collapse navbar-collapse"  id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">입/출고 관리</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="/web/order">지점 주문 관리</a>
					<a class="dropdown-item" href="/web/order_p2p">지점간 이동 주문 관리</a>
				</div>
			</li>
			
			<li class="nav-item">
				<a class="nav-link" href="/web/productList">상품 관리</a>
			</li>
			
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">통계</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="/web/company_chart">매출 통계</a>
					<a class="dropdown-item" href="/web/p2p_chart">지점간 이동 통계</a>
				</div>
			</li>
			
			<li class="nav-item">
				<a class="nav-link" href="/web/Notice_list?page=1">공지 사항</a>
			</li>
		</ul>
			
		<form class="form-inline" action="/web/logout" method="post">
			<input type="text" value="${user_id}" placeholder="Search" aria-label="Search" class="form-control mr-sm-2" readonly>
			<button type="submit" class="btn btn-outline-success my-2 my-sm-0">로그아웃</button>
		</form>
	</div>
</nav>