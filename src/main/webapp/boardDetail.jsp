<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 상세</title>
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
		
		<tr style="height:50px;">
			<td style="border:none;">
				<a href="boardEdit.do?b_idx=${board.b_idx}" style="width:70%;font-weight:700;background-color:#818181;color:#fff;">수정</a>
			</td>
			<td style="border:none;">
				<a href="reply.do?b_group=${board.b_group}&b_order=${board.b_order}&b_depth=${board.b_depth}" style="width:70%;font-weight:700;background-color:blue;color:#fff;">답글</a>	
			</td>
			<td style="border:none;">
				<a href="boardDelete.do?b_idx=${board.b_idx}" style="width:70%;font-weight:700;background-color:red;color:#fff;">삭제</a>	
			</td>	

		</tr>
	</table>
 
 	<form method="post" action="comment-write.do">
    	<input type="hidden" name="b_group" value="${board.b_group}">
        <input type="hidden" name="b_order" value="${board.b_order}">
        <input type="hidden" name="b_depth" value="${board.b_depth}">
        <p> 댓글내용 : <textarea name="c_comment" rows="5" cols="100"></textarea></p>
		<p> <input type="hidden" name="c_date" value="${comment.c_date}"></p>		   		
		<p> <input type="submit" value="작성 완료"></p>
	</form>
 
 	<table>
 		<c:forEach items="${board.commentList}" var="comment" varStatus="status">
			<tr>
				<td>댓글</td>
				<td>${comment.c_comment}</td>
				<td>작성시간</td>
				<td>${comment.c_date}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
