<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품목록2</title>
</head>
<style>
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
	}
	td	{
	
		text-align:center;
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
		text-decoration:none;
		color:#000;
		font-weight:700;
	}
	ul {
		width:600px;
		height:50px;
		margin:10px auto;
	}
	li {
		list-style:none;
		width:50px;
		line-height:50px;
		border:1px solid #ededed;
		float:left;
		text-align:center;
		margin:0 5px;
		border-radius:5px;
	}
</style>
<body>
<h1>작품 목록</h1>
	<table >
		<tr>
			<td colspan="3">전체 작품 수 : ${pagination.count}</td>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>내용</th>
			<th>조회수</th>
			<th>작성자</th>
			<th>작성일자</th>
			<th>이미지</th>
		</tr>
		<c:forEach items="${blist}" var="item" varStatus="status">
			 <tr>
				<td><a href="board-Detail.do?b_idx=${item.b_idx}">${item.rownum}</a></td>
				<td>${item.b_title}</td>
				<td>${item.b_content}</td>
				<td>${item.b_assistant}</td>
				<td>${item.b_writer}</td>
				<td>${item.b_date}</td>
				<td>${item.bf_filename}</td>
		     <tr>
		</c:forEach>
	
		<tr align ="center">
			<td colspan="7">
				<form action="board-blist.do" method="GET">
					<select name = "selectBox" >
						<option value = "b_title"> 제목으로 검색</option>
						<option value = "b_content"> 내용으로 검색 </option>
						<option value = "b_writer"> 이름으로 검색</option>
						
					</select>
				
					<input type= "text" name = "searchText">
					<input type= "submit" value="검색" >
				</form>
			</td>
		</tr>
		</table>
	<div>
		<ul> 
			 <c:choose>
				<c:when test="${pagination.prevPage < 5}">
					<li>
						<a href="board-blist.do?page=${pagination.prevPage}">◀</a>
					</li>
				</c:when>
			</c:choose> 
			<c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
				
					<c:choose>
						<c:when test="${ pagination.page eq i }">
							
							<li style="background-color:#ededed;">
								<span>${i}</span>
							</li>
						</c:when>
						<c:when test="${ pagination.page ne i }">
							<li>
								<a href="board-blist.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.endPage < pagination.lastPage }">
					<li style="">
						<a href="board-blist.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>
