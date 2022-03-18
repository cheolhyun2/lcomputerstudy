<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품 수정</title>
</head>
<body>
        <form method="post" action="boardEditProcess.do" >
          	<input type="hidden" name="b_idx" value="${board.b_idx}">
	   		<p> 제목 : <input type="text" name="edit_title" value="${board.b_title}"></p>
	   		<p> 내용 : <textarea name="edit_content" rows="5" cols="100">${board.b_content}</textarea></p>
	   		<p> 조회수 : <input type="text" name="edit_assistant" value="${board.b_assistant}"></p>
	   		<p> 작성자 : <input type="text" name="edit_writer" value="${board.b_writer}"></p>		   
	   		<p> 작성일자 : <input type="date" name="edit_date" value="${board.b_date}"></p>
	   		
	   		<p> <input type="submit" value="수정완료"></p>
     	 </form>
     	 
     
	
</body>
</html>
