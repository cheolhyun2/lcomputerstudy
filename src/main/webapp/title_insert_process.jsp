<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>작품등록 값 받아오기</title>
</head>
<body>
<%@ include file="db_connection.jsp" %>
<%

	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");
	String content = request.getParameter("content");
	String assistant = request.getParameter("assistant");
	String writer = request.getParameter("writer");
	String date = request.getParameter("date");
	
	
	PreparedStatement pstmt = null;
	
	try {
		String sql = "insert into board(b_title,b_content,b_assistant,b_writer,b_date) values(?,?,?,?,?,)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, title);
		pstmt.setString(2, content);
		pstmt.setString(3, assistant);
		pstmt.setString(4, writer);
		pstmt.setString(5, date);
		pstmt.executeUpdate();
	} catch(SQLException ex) {
		System.out.println("SQLException : "+ex.getMessage());
	} finally {
		if(pstmt != null) {
			pstmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
%>
<h3>저장 완료</h3>
</body>
</html>
