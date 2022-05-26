<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 상세</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
</head>
<style>
	table {
		border-collapse:collapse;
	}
	table tr th {
		font-weight:700;
	}
	table tr td, table tr th {
		border:1px solid #818181;
		width:200px;
		text-align:center;
	}
	a {
		text=decoration:none;
		color:#000;
		font-weight:700;
		border:none;
		cursor:pointer;
		padding:10px;
		display:inline-block;
	
	  }
</style>
<body>
	<h1>작품 상세페이지</h1>
	<table>
		<tr>
			<td>작품 번호</td>
			<td>${board.b_idx}</td>		
		</tr>
		<tr>
			<td>작품 제목</td>
			<td>${board.b_title}</td>		
		</tr>
		<tr>
			<td>작품 내용</td>
			<td>${board.b_content}</td>		
		</tr>
		<tr>
			<td>작품 조회수</td>
			<td>${board.b_assistant}</td>		
		</tr>
		<tr>
			<td>작품 작성자</td>
			<td>${board.b_writer}</td>		
		</tr>
		<tr>
			<td>작품 작성일자</td>
			<td>${board.b_date}</td>
		</tr>
		<tr>
			<td>이미지</td>
			<td><img src = "/lcomputerstudy/img/${boardfile.bf_filename}"></td>
			
		</tr>		
		
		<tr style="height:50px;">
			<c:choose>
				<c:when test = "${sessionScope.user.u_level==3}">
					<td style="border:none;">
					<a href="boardEdit.do?b_idx=${board.b_idx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;">수정</a>
					</td>
				</c:when>
			</c:choose>
					<td style="border:none;">
					<a href="reply.do?b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}" style="width:70%;font-weight:700;background-color:blue;color:#fff;">답글</a>	
					</td>
			<c:choose>
				<c:when test ="${sessionScope.user.u_level==1 or sessionScope.user.u_level==3}">
					<td style="border:none;">
					<a href="boardDelete.do?b_idx=${board.b_idx}" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>	
					</td>
				</c:when>
			</c:choose>	

		</tr>
	</table>
 
 	<form method="post" action="commentProcess.do">
 		<input type="hidden" name="b_idx" value="${board.b_idx}">
        <p> 댓글내용 : <textarea name="c_comment" rows="5" cols="100"></textarea></p>
		<p> <input type="submit" value="작성 완료"></p>
	</form>
	
	<form action="commentReplyProcess.do" method="post" id="commentReplyForm">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
		<input type="hidden" name="c_group" value="0">
		<input type="hidden" name="c_order" value="0">
		<input type="hidden" name="c_depth" value="0">
		<input type="hidden" name="c_comment" value="">
	</form>
	
	<form action="commentUpdateProcess.do" method="post" id="commentUpdate">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
		<input type="hidden" name="c_group" value="0">
		<input type="hidden" name="c_order" value="0">
		<input type="hidden" name="c_depth" value="0">
		<input type="hidden" name="c_comment" value="">
	</form>
	
	<form action="commentDeleteProcess.do" method="post" id="commentDeleteForm">
		<input type="hidden" name="b_idx" value="${board.b_idx}">
		<input type="hidden" name="c_group" value="0">
		<input type="hidden" name="c_order" value="0">
		<input type="hidden" name="c_depth" value="0">
		<input type="hidden" name="c_comment" value="">
	</form>
	
 
 	<table id="commentListTbl">
 		<c:forEach items="${board.commentList}" var="comment" varStatus="status">
			<tr>
				<td>댓글</td>
				<td>${comment.c_comment}</td>
				<td>작성시간</td>
				<td>${comment.c_date}</td>
				<td>
					<button type="button" class="commentForm">댓글추가</button>
					<button type="button" class="commentRetouch">댓글수정</button>
					<button type="button" class="commentDelete" c_idx="${comment.c_idx}">댓글삭제</button>
					
				</td>
				
			</tr>
			<!-- 대댓글 -->
			<tr style="display: none;">
				<td colspan="4"><textarea rows="2" name="c_comment" cols="100"></textarea></td>
				<td>
					<button type="button" class="commentRed" c_group="${comment.c_group}" c_order="${comment.c_order}" c_depth="${comment.c_depth}">등록</button>
					<button type="button" class="commentCancel">취소</button>
				</td>
			</tr>
			<!-- 댓글 수정 -->
			<tr style="display: none;">
				<td>수정댓글</td>
				<td><textarea rows="2" cols="100">${comment.c_comment}</textarea></td>
				<td>작성시간</td>
				<td>${comment.c_date}</td>
				<td>
					<button type="button" class="commentUpdate" c_group="${comment.c_group}" c_order="${comment.c_order}" c_depth="${comment.c_depth}" c_idx="${comment.c_idx}">수정</button>
					<button type="button" class="commentUpdateCancel">취소</button>
				</td>
			</tr>
			
		</c:forEach>
	</table>
	<script>
	$(document).on('click', '.commentForm', function () {
		$(this).parent().parent().next().css('display', '');
	});
	$(document).on('click', '.commentRed', function () {
		let group = $(this).attr('c_group');
		$('#commentReplyForm').find('input[name="c_group"]').val(group);
		
		let order = $(this).attr('c_order');
		$('#commentReplyForm').find('input[name="c_order"]').val(order);
		
		let depth = $(this).attr('c_depth');
		$('#commentReplyForm').find('input[name="c_depth"]').val(depth);
		
		let comment = $(this).parent().prev().find('textarea').val();
		$('#commentReplyForm').find('input[name="c_comment"]').val(comment);
		//$('#commentReplyForm').submit();
		
		let b_idx = ${board.b_idx}; 
		
		$.ajax({
		  method: "POST",
		  url: "commentReplyForm.do",
		  data: { b_idx: b_idx, c_group: group, c_order: order, c_depth: depth, c_comment: comment }
		})
		.done('click','.commentRed', function( html ) {
		  //console.log(html);
		  $('#commentListTbl').html(html);
		});
	});
	$(document).on('click', '.commentCancel', function () {
		$(this).parent().parent().css('display', 'none');
	});
	
	$(document).on('click', '.commentRetouch', function () {
		//$(this).parent().parent().next().css('display', '');
		$(this).parent().parent().next().next().css('display', '');
		$(this).parent().parent().css('display', 'none');
	});
	
	$(document).on('click', '.commentUpdate', function () {
		let group = $(this).attr('c_group');
		let order = $(this).attr('c_order');
		let depth = $(this).attr('c_depth');		
		let comment = $(this).parent().parent().find('textarea').val();
		let b_idx = ${board.b_idx}; 
		let c_idx = $(this).attr('c_idx'); 

		console.log(comment);
		
		$.ajax({
		  method: "POST",
		  url: "commentUpdate.do",
		  data: { b_idx: b_idx, c_group: group, c_order: order, c_depth: depth, c_comment: comment, c_idx: c_idx }
		})
		.done('click','.commentUpdate', function( html ) {
		  //console.log(html);
		  $('#commentListTbl').html(html);
		});
	});
	$(document).on('click', '.commentUpdateCancel', function () {
		location.reload();
	});
	
	$(document).on('click', '.commentDelete', function () {
		let group = $(this).attr('c_group');
		let order = $(this).attr('c_order');
		let depth = $(this).attr('c_depth');		
		let b_idx = ${board.b_idx}; 
		let c_idx = $(this).attr('c_idx'); 

				
		$.ajax({
		  method: "POST",
		  url: "commentDelete.do",
		  data: { b_idx: b_idx, c_group: group, c_order: order, c_depth: depth, c_idx: c_idx }
		})
		.done('click','.commentUpdate', function( html ) {
			  //console.log(html);
		  $('#commentListTbl').html(html);
		});
	});
	</script>
</body>
</html>
