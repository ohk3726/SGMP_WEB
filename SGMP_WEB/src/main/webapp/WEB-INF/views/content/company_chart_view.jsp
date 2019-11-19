<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- post한글처리 -->
<% request.setCharacterEncoding("UTF-8"); %>

<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<div style="display:inline;width:100%;">
	<div style="width:50%;margin:0 auto;background-color:white;display:inline;float:left;">
		<canvas id="myChart" class="chartjs" style="width:100%;height:100%"></canvas>
	</div>
	
	<div style="width:50%;margin:0 auto;background-color:white;display:inline;float:left;">
		<canvas id="myChart_1" class="chartjs" style="width:100%;height:100%"></canvas>
	</div>
</div>


<script>
var ctx = document.getElementById("myChart"); //캔버스 id값 가져오기
var myChart = new Chart(ctx, {
    type: 'doughnut', //그래프 형태 지정하기
    data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"], //X축 제목
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor:[
            	'rgba(190,190,190,1)',
            	'rgba(241,196,15,1)',
            	'rgba(244,7,7,1)',
            	'rgba(52,152,219,1)',
            	'rgba(46,204,113,1)',
            	'rgba(241,7,190,1)'
            ]
        }]
    }
});

var ctx = document.getElementById("myChart_1"); //캔버스 id값 가져오기
var myChart = new Chart(ctx, {
    type: 'doughnut', //그래프 형태 지정하기
    data: {
        labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"], //X축 제목
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            backgroundColor:[
            	'rgba(190,190,190,1)',
            	'rgba(241,196,15,1)',
            	'rgba(244,7,7,1)',
            	'rgba(52,152,219,1)',
            	'rgba(46,204,113,1)',
            	'rgba(241,7,190,1)'
            ]
        }]
    }
});
</script>