<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 
<script>

var bno = '${Notice_list_info.no_bid}'; //게시글 번호
 
$('[name=mentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시 
    var insertData = $('[name=mentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
    commentInsert(insertData); //Insert 함수호출(아래)
});
 
 
 
//댓글 목록 
function commentList(){
    $.ajax({
        url : '/ment/list',
        type : 'get',
        data : {'bno':bno},
        success : function(data){
            var a =''; 
            $.each(data, function(key, value){ 
                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
                a += '<div class="commentInfo'+value.ment_id+'">'+'댓글번호 : '+value.ment_id+' / 작성자 : '+value.ment_writer;
                a += '<a onclick="commentUpdate('+value.ment_id+',\''+value.ment_content+'\');"> 수정 </a>';
                a += '<a onclick="commentDelete('+value.ment_id+');"> 삭제 </a> </div>';
                a += '<div class="commentContent'+value.ment_id+'"> <p> 내용 : '+value.ment_content +'</p>';
                a += '</div></div>';
            });
            
            $(".mentList").html(a);
        }
    });
}
 
 //===============================================================================
//댓글 등록
function commentInsert(insertData){
    $.ajax({
        url : '/insert',
        type : 'post',
        data : insertData,
        success : function(data){
            if(data == 1) {
                commentList(); //댓글 작성 후 댓글 목록 reload
                $('[name=content]').val('');
            }
        }
    });
}
 
//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
function commentUpdate(ment_id, content){
    var a ='';
    
    a += '<div class="input-group">';
    a += '<input type="text" class="form-control" name="content_'+ment_id+'" value="'+content+'"/>';
    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+ment_id+');">수정</button> </span>';
    a += '</div>';
    
    $('.commentContent'+cno).html(a);
    
}
 
//댓글 수정
function commentUpdateProc(ment_id){
    var updateContent = $('[name=content_'+ment_id+']').val();
    
    $.ajax({
        url : '/ment/update',
        type : 'post',
        data : {'content' : updateContent, 'ment_id' : ment_id},
        success : function(data){
            if(data == 1) commentList(notice_id); //댓글 수정후 목록 출력 
        }
    });
}
 
//댓글 삭제 
function commentDelete(ment_id){
    $.ajax({
        url : '/ment/delete/'+ment_id,
        type : 'post',
        success : function(data){
            if(data == 1) commentList(notice_id); //댓글 삭제후 목록 출력 
        }
    });
}
 
 
 
 
$(document).ready(function(){
    commentList(); //페이지 로딩시 댓글 목록 출력 
});
 
 
 
</script>
