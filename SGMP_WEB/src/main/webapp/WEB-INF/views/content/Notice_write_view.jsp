<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<div style="padding-top:80px;">
	<div style="background-color:#464646;text-align:right;">
		<a href="Notice_list?page=1" class="btn btn-dark">목록보기</a>
	</div>
	<div class="mx-auto" style="background-color:white;height:100%;">
		<form action="write" method="post">
			<table class="table" >
				<thead class="thead-dark">
					<tr>
						<th scope="col" style="text-align:center;vertical-align:middle;">작성자명</th>
						<td> <input type="text" name="bName" class="form-control"> </td>
					<tr>
						<th scope="col" style="text-align:center;vertical-align:middle;">제목</th>
						<td> <input type="text" name="bTitle" class="form-control"> </td>
					<tr>
						<th scope="col" style="text-align:center;vertical-align:middle;">내용</th>
						<td><textarea name="bContent" rows="10" class="form-control"></textarea> </td>
							<input type="hidden" name="page" value="1">
					<tr>
						<td colspan="2"> <input type="submit" value="입력" class="btn btn-secondary"></td>
				</thead>
			</table>
		</form>
	</div>
</div>