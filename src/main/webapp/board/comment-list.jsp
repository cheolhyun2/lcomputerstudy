<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:forEach items="${commentList}" var="comment" varStatus="status">
	<tr>
		<td>댓글</td>
		<td>${comment.c_comment}</td>
		<td>작성시간</td>
		<td>${comment.c_date}</td>
		<td>
			<button type="button" class="commentForm">댓글추가</button>
			<button type="button" class="commentRetouch">댓글수정</button>
			<button type="button" class="commentDelete">댓글삭제</button>
			
		</td>
	</tr>
	<tr style="display: none;">
		<td colspan="4"><textarea rows="2" name="c_comment" cols="100"></textarea></td>
		<td>
			<button type="button" class="commentRed" c_group="${comment.c_group}" c_order="${comment.c_order}" c_depth="${comment.c_depth}">등록</button>
			<button type="button" class="commentCancel">취소</button>
		</td>
		
	</tr>
	
	
	<tr style="display: none;">
		<td colspan="4"><textarea rows="2" name="c_comment" cols="100"></textarea></td>
		<td>
			<button type="button" class="commentUpdate" c_comment= "${comment.c_comment}" c_group="${comment.c_group}" c_order="${comment.c_order}" c_depth="${comment.c_depth}" c_idx="${comment.c_idx}">수정</button>
			<button type="button" class="commentUpdateCancel">취소</button>
		</td>
		
	</tr>
</c:forEach>
