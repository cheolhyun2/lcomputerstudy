<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록2</title>
</head>
<style>
	h1 {
		text-align:center;
	}
	table {
		border-collapse:collapse;
		margin:40px auto;
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
<h1>회원 목록</h1>
	<table >
		<tr>
			<td colspan="5">전체 회원 수 : ${pagination.count}</td>
		<tr>
			<th>No</th>
			<th>ID</th>
			<th>이름</th>
			<th>권한</th>
			<th>권한변경</th>
		</tr>
		<c:forEach items="${list}" var="user" varStatus="status">
			 <tr>
				<td><a href="user-detail.do?u_idx=${user.u_idx}">${user.rownum}</a></td>
				<td>${user.u_id}</td>
				<td>${user.u_name}</td>
				<td>${user.u_level}</td>
				<td>
					<form action = "level-insert-process.do" method="post">
						<input type="hidden" name="u_idx" value="${user.u_idx}">
						<select name = "u_level">
							<option	value = "1">관리자</option>
							<option value = "3">사용자</option>
										
						</select>
						<button type = "submit">권한변경</button>
					</form>
				</td>
		     <tr>
		</c:forEach>
	</table>
	<div>
		<ul>
			 <c:choose>
				<c:when test="${pagination.prevPage < 5}">
					<li>
						<a href="user-list.do?page=${pagination.prevPage}">
							◀
						</a>
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
								<a href="user-list.do?page=${i}">${i}</a>
							</li>
						</c:when>
					</c:choose>
			</c:forEach>
			 <c:choose>
				<c:when test="${ pagination.endPage < pagination.lastPage }">
					<li style="">
						<a href="user-list.do?page=${pagination.nextPage}">▶</a>
					</li>
				</c:when>
			</c:choose> 
		</ul>
	</div>
</body>
</html>
