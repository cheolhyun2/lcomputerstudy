<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대댓글 달기</title>
</head>
<body>
<%@ include file="db_connection.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String c_comment = request.getParameter("c_comment");
		
	
	PreparedStatement pstmt = null;
	
	try {
		String sql = "insert into comment(c_comment) values(?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, c_comment);
		
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
<h3></h3>
</body>
</html>
