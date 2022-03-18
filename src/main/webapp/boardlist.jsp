<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 작품 목록 </title>
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
</style>
<body>
<%@ include file="db_connection.jsp" %>
	<h1>작품 목록</h1>
	<table >
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>내용</th>
			<th>조회수</th>
			<th>작성자</th>
			<th>작성일자</th>
		</tr>
		<%
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
		    String query = "select * from board";
	       	pstmt = conn.prepareStatement(query);
	       	
	        rs = pstmt.executeQuery();
	
	        while(rs.next()){     
	       	       String b_idx = rs.getString("b_idx");
	               String b_title = rs.getString("b_title");
	               String b_content = rs.getString("b_content");
	               String b_assistant = rs.getString("b_assistant");
	               String b_writer = rs.getString("b_writer");
	               String b_date = rs.getString("b_date");
	     %>
	     <tr>
	     	<td><a href="boardDetail.jsp?u_idx=<%=b_idx%>"><%=b_idx %></a></td>
    		<td><%=b_idx %></td>
			<td><%=b_title %></td>
			<td><%=b_content %></td>
			<td><%=b_assistant %></td>
			<td><%=b_writer %></td>
			<td><%=b_date %></td>
	     <tr>
		<%
	        }
	        rs.close();
	        pstmt.close();
	        conn.close();
		%>
		
			
	</table>
</body>
</html>
