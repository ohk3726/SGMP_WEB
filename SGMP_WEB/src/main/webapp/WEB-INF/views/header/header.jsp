<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="resources/css/header.css?ver=1">
	<div id="bar">
		<div id="headertitle"><a href="/web/main">BSM</a></div>
		<div id="menubar">
			<ul>
				<li><a href="#">입/출고 관리</a>
					<ul>
						<li><a href="#">상품 주문</a></li>
						<li><a href="/web/order">지점 주문 관리</a></li>
						<li><a href="#">지점간 이동</a></li>
					</ul>
				</li>
				<li><a href="#">상품 관리</a>
					<ul>
						<li><a href="/web/productList">상품 재고관리</a></li>
						<li><a href="#">상품 등록</a></li>
					</ul>
				</li>
				<li><a href="#">거래처 관리</a></li>
				<li><a href="#">정산 관리</a>
					<ul>
						<li><a href="#">지점별 정산 관리</a></li>
						<li><a href="#">분류별 정산 관리</a></li>
					</ul>
				</li>
				<li><a href="#">공지 사항</a></li>
			</ul>
		</div>
		<div>
				${user_id}
				<form action="/web/logout" method="post">
					<input type="submit" value="로그아웃">
				</form>
		</div>
	</div>