<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색된 작품</title>
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
	<h1>검색된 작품 </h1>
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
	</table>
</body>
</html>
