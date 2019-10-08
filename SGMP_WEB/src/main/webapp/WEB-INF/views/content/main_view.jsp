<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="resources/js/zabuto_calendar.min.js"></script>

<link rel="stylesheet" type="text/css" href="resources/css/contents.css">
<link rel="stylesheet" type="text/css" href="resources/css/zabuto_calendar.min.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
<div id="contents">
	<div id="user">
		사용자 부분
	</div>
	<div id="calendar">
		<div id="calendar1"></div>
		<script type="application/javascript">
			$(document).ready(function(){
				$("#calendar1").zabuto_calendar({
					language:"kr",
					cell_border:true,
					today: true,
					show_days:false,
					weekstartson:0,
					nav_icon:{
						prev : '<i class="fa fa-chevron-circle-left"></i>',
						next : '<i class="fa fa-chevron-circle-right"></i>'
					}
				});
			});
		</script>
	</div>
	
	<div id="chart">
		간략한 통계 부분
	</div>
	<div id="notices">
		공지사항 상위 5개 부분
	</div>
	<div id="sale_rank">
		상품 판매순위 상위 20개
	</div>
	<div id="stock_rank">
		재고 부족 상위 20개
	</div>
	<div id="product_stock_cnt">
		품목별 재고 수량
	</div>
</div>

