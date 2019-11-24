<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta name="MSSmartTagsPreventParsing" content="TRUE" />
<meta http-equiv="expires" content="-1" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="Tom Cryns" />
<meta name="description" content="webtom grey website template" />
<!-- 댓글 새로고침 수정 삭제,  게시글 수정,삭제,검색일때 paging 안됌, 목록보기, 게시글이 이상하게 정열됌-->
<script>
function commentDelete(ment_ID,bid){
	
    $.ajax({
        url : 'delete/'+ment_ID,
        type : 'post',
        
        success : function(){
        	
        	listReply2(bid);

        }
    });
}

function commentUpdate(ment_ID, ment_CONTENT,bid){
    var a ='';
    
    a += '<div class="input-group">';
    a += '<input type="textarea" cols="30" rows="10" class="form-control" name="content_'+ment_ID+'" value="'+ment_CONTENT+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+ment_ID+','+bid+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+ment_ID).html(a);
    
}

function commentUpdateProc(ment_ID,bid){
    var updateContent = $('[name=content_'+ment_ID+']').val();
    
    $.ajax({
        url : 'update',
        type : 'post',
        data : {'content' : updateContent, 'ment_id' : ment_ID},
        success : function(data){
           //댓글 수정후 목록 출력 
        	listReply2(bid);
        }
    });
}

function listReply(){
	$.ajax({
		type: "get",
		url : "list?no_bid="+bid,
		success: function(result){
			console.log(JSON.stringify(result));
			$("#listReply").html(result);
			alert("asdzxczx");
		}
		
	});
}

function listReply2(bid){

	$.ajax({
		type: "get",
		url :"listJson?no_bid="+bid,
		dataType:"json",
		success: function(result)
		{
			//console.log(JSON.stringify(result));
			//alert(result);
			var output = "<table class='table'>";
			for(var i in result){
				output += "<tr>";
				output +="<td>" + result[i].ment_WRITER;
				output += '<td><div style="width:500px;" class="commentContent'+result[i].ment_ID+'">내용 : '+result[i].ment_CONTENT;
				output +="<td>("+result[i].ment_DATE+")";
				output += '<td><a onclick="commentDelete('+result[i].ment_ID+','+bid+');" class="btn btn-secondary" style="float:right;"> 삭제 </a>';
				output += '<a onclick="commentUpdate('+result[i].ment_ID+',\''+result[i].ment_CONTENT+'\','+bid+');" class="btn btn-secondary" style="float:right;">수정</a>';
			
			}
			output += "</table>";

			$("#listReply").html(output);
		
		}
	});
	
}
function changeDate(date){
	date = new Date(parseInt(date));
	year = date.getFullYear();
	month = date.getMonth();
	day = date.getDate();
	hour = date.getHours();
	minute = date.getMinutes();
	second = date.getSeconds();
	strDate = year+"-"+month+"-"+day+" " + hour+" : "+minute+" : "+second;
	return strDate;
	
}

</script>
<title>야구 용품 관리(본사용)</title>
</head>

<body>
	<form action="modify" method="post">
		<div style="width:80%;padding-top:80px;" class="mx-auto">
			<table cellpadding="0" cellspacing="0" class="table">
				<c:forEach items="${Notice_list_info}" var="Notice_list_info">
					<script>
					var no_bid_info = '${Notice_list_info.no_bid}'; //게시글 번호
					</script>
	
					<input type="hidden" name="page" id ="page"value="1">
					<input type="hidden" name="bId" id ="bId"value="${Notice_list_info.no_bid}">
					<thead class="thead-dark">
					<tr>
						<th scope="col"  style="text-align:center;vertical-align:middle;">이름</th>
						<td><input type="text" name="bName" value="${Notice_list_info.no_name}" readonly class="form-control-plaintext"></td>
						<th scope="col"  style="text-align:center;vertical-align:middle;">조회수</th>
						<td>${Notice_list_info.no_hit}</td>
					<tr>
						<th scope="col"  style="text-align:center;vertical-align:middle;">제목</th>
						<td colspan="3"><input type="text" name="bTitle" value="${Notice_list_info.no_title}" class="form-control"></td>
					<tr>
						<th scope="col" style="text-align:center;vertical-align:middle;">내용</th>
	
						<td colspan="3"><textarea rows="10" name="bContent" class="form-control" style="resize:none;">${Notice_list_info.no_content}</textarea>
					<tr>
						<td colspan="4"><input type="submit" value="수정"  class="btn btn-secondary"> 
						<a href="Notice_delete?no_bid=${Notice_list_info.no_bid}" class="btn btn-secondary">삭제</a>
						<a href="Notice_list?page=1" class="btn btn-secondary">목록보기</a></td>
					</thead>
				</c:forEach>
			</table>
		</div>
	</form>
	
	 <div id="listReply" style="width:80%;" class="mx-auto">
	</div>
	
	<form action="insert" method="post">
		<div style="width:80%;" class="mx-auto">
			<br>
				<textarea rows="5" cols="80" id="replytext" placehoder="댓글을 작성해주세요." class="form-control" style="resize:none;"></textarea>
			<br>
			<button type="button" id="btnReply" class="btn btn-secondary">댓글 작성</button>
		</div>
		<input type="hidden" name="bId" id ="bId" value="${no_bid_info}">
	</form>


<script>
$(document).ready(function(){
	var bid = document.getElementById("bId").value;
	listReply2(bid);
	
	$("#btnReply").click(function(){
		var replytext = $("#replytext").val();
		var no_bid="{bId.value}";
		
		var param="replytext=" + replytext+" &no_bid="+bid;
		
		$.ajax({
			type: "post",
			url : "insert",
			data: param,
			success : function(bid){

				listReply2(bid);
			}
		});
	});
	
});

</script>

</body>
</html>