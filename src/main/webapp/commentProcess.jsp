<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 작성</title>
</head>
<body>
        <form method="post" action="boardDetail.do" >
        	
      	  	<input type="hidden" name="b_group" value="${board.b_group}">
          	<input type="hidden" name="b_order" value="${board.b_order}">
          	<input type="hidden" name="b_depth" value="${board.b_depth}">
	   		
	   	<p> 댓글내용 : <textarea name="c_comment" rows="5" cols="100"></textarea></p>
	   			   		
	   		<p> <input type="submit" value="완료"></p>
     	 </form>
     	 
     
	
</body>
</html>
