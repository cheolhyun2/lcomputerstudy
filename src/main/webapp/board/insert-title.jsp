<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 작품 등록 </title>
</head>
<body>

	<h2> 작품 등록 </h2>
	<form action="board-title-insert-process.do" name="board" method="post" enctype="multipart/form-data">
	
		<p> 제목 : <input type="text" name="b_title">
		<p> 내용 : <input type="text" name="b_content">
		<p> 작성자 : <input type="text" name="b_writer">
		</p>
	
		<p> 파일 : <input type="file" name="bf_filename"></p>
		<p> 파일2: <input type="file" name="bf_filename2"></p>

		
		<p> <input type="submit" value="등록하기"></p>
	</form>

</body>
</html>
