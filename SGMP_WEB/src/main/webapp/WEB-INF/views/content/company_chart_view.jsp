<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<div style="width:100%;padding-top:80px;">
	<div class="row">
		<div class="col-6" style="padding-right:1px;height:30%;">
			<table class="table" ><thead class="thead-dark"><tr><th>최근 7일간 판매량</thead></table>
			<canvas id="myChart1" height=80%></canvas>
		</div>
		<div class="col-6" style="padding-left:1px;height:30%;">
			<table class="table" ><thead class="thead-dark"><tr><th>최근 7일 카테고리별 판매량</thead></table>
			<canvas id="myChart2" height=80%></canvas>
		</div>
	</div>
	<div class="col-12"  style="padding-right:0px;padding-left:0px;">
		<table class="table" id="category_list">
			<thead class="thead-dark">
				<tr>
					<th scope="col" colspan="4">선택 카테고리 리스트
				<tr>
					<th scope="col">제품ID
					<th scope="col">제품명
					<th scope="col">판매량
					<th scope="col">판매금액
			</thead>
		</table>
	</div>
</div>


<script>
	var ctx = document.getElementById('myChart2').getContext('2d');
	var myData = [${chart_y}];
	var chart2 = new Chart(ctx,{
		type : 'bar',
		data : {
			labels:[${chart_x}],
			datasets:[{
				borderColor:'blue',
				data:myData,
				backgroundColor:['green','aqua','purple','hotpink','blue','red']
			}]
		},
		options:{
			onClick: function(evt, activeElements){
				
			},
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
	
	var ctx = document.getElementById('myChart1').getContext('2d');
	var chart1 = new Chart(ctx,{
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
	
	document.getElementById("myChart2").onclick = function(evt){
		var activePoints = chart2.getElementsAtEvent(evt);
		if(activePoints.length > 0){
			var clickedElementindex = activePoints[0]["_index"];
			var prod_main_category = chart2.data.labels[clickedElementindex];
			$.ajax({
				url:"select_category?prod_main_category="+prod_main_category,
				type:"post",
				dataType:"text",
				success:function(result){
					console.log(result);
					$('#category_list').empty();
					$('#category_list').html(result);
				},
				 error:function(request,status,error){
				        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				       }
			});
		}
	}
</script>