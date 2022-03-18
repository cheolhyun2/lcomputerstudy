<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 작품 등록 </title>
</head>
<body>

	<h2> 작품 등록 </h2>
	<form action="board-title-insert-process.do" name="board" method="post">
	
		<p> 제목 : <input type="text" name="title">
		<p> 내용 : <input type="text" name="content">
		<p> 조회수 : <input type="text" name="assistant">
		<p> 작성자 : <input type="text" name="writer">
		<p> 작성일지 :  <input type="date" name="writetime"> 
			    
		</p>
	
		<p> <input type="submit" value="등록하기"></p>
</form>

</body>
</html>
