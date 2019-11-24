<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="resources/js/zabuto_calendar.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/zabuto_calendar.min.css">
<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script>
	function select(no_bid){
		location.href="Notice_list_info?no_bid="+no_bid;
	}
</script>
<div style="width:100%;padding-top:80px;">
  <div class="row">
    <div class="col-2 text-center" style="padding:20px;">
    	<div class="portlet light profile-sidebar-portlet bordered">
	    	<div class="profile-userpic">
	    		<img src="https://bootdey.com/img/Content/avatar/avatar6.png" style="width:150px;" class="rounded-circle" alt="">
	    	</div>
	    	<div class="profile-usertitle">
	        	<h1>${user_id}</h1>        
	        </div> 	
	       	<form action="/web/logout" method="post">
				<button type="submit" class="btn btn-primary">로그아웃</button>
			</form>			
     	</div>
    </div>
    <div class="col-6" style="padding-left:1px;padding-right:1px;">
    	<table class="table" ><thead class="thead-dark"><tr><th>최근 7일간 판매량</thead></table>
   		<div>
   			<canvas id="myChart" height="100"></canvas>
   		</div>
    </div>
    <div class="col-4" style="padding-left:1px;">
    	<table class="table table-hover" >
			<thead class="thead-dark">
				<tr>
					<th scope="col" colspan="5">최신 개시글
				<tr class="text-center">
					<th scope="col">번호</th>
					<th scope="col">제목</th>
					<th scope="col">이름</th>
					<th scope="col">날짜</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="notice_list">
					<tr class="text-center" onclick="javascript:select(${notice_list.no_bid});">
						<td>${notice_list.no_rownum}</td>
						<td><c:forEach begin="1" end="${notice_list.no_indent}">-</c:forEach>${notice_list.no_title}</td>
						<td>${notice_list.no_name}</td>
						<td>${notice_list.no_date}</td>
						<td>${notice_list.no_hit}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
    </div>
    <div class="col-6" style="padding-right:1px;">
		<table class="table table-hover" >
			<thead class="thead-dark">
				<tr>
					<th scope="col" colspan="5">TOP 10 제품
				<tr class="text-center">
					<th scope="col">제품번호</th>
					<th scope="col" style="width:400px;">제품명</th>
					<th scope="col">판매량</th>
					<th scope="col">판매금액</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list2}" var="top_list">
					<tr class="text-center">
						<td>${top_list.prod_id}</td>
						<td>${top_list.prod_name}</td>
						<td>${top_list.prod_cnt}</td>
						<td>${top_list.prod_price}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-6" style="padding-left:1px;">
		<table class="table table-hover" >
			<thead class="thead-dark">
				<tr>
					<th scope="col" colspan="5">재고부족 상품
				<tr class="text-center">
					<th scope="col">제품번호</th>
					<th scope="col" style="width:400px;">제품명</th>
					<th scope="col">재고수량</th>
					<th scope="col">최소재고수량</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list1}" var="cnt_min">
					<tr class="text-center">
						<td>${cnt_min.prod_id}</td>
						<td>${cnt_min.prod_name}</td>
						<td>${cnt_min.prod_cnt}</td>
						<td>${cnt_min.prod_cnt_min}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
  </div>
</div>
<script>
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx,{
		type : 'line',
		data : {
			labels:[${chart_1}],
			datasets:[{
				borderColor:'blue',
				data:[${chart_2}]
			}]
		},
		options:{
			legend:{
				display:false
			},
			tooltips: {
		          callbacks: {
		                label: function(tooltipItem, data) {
		                    var value = data.datasets[0].data[tooltipItem.index];
		                    if(parseInt(value) >= 1000){
		                               return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		                            } else {
		                               return value;
		                            }
		                }
		          }
		        },
			scales: {
	            yAxes: [{
	                ticks: {
	                	fontSize:20,
	                    beginAtZero:true,
	                    callback: function(value, index, values) {
	                        if(parseInt(value) >= 1000){
	                           return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	                        } else {
	                           return value;
	                        }
	                   }                            
	                }
	            }],
	            xAxes: [{
	                ticks: {
	                	fontSize:20,
	                    beginAtZero:true
	                }
	            }]
	        }
		}
	});
</script>