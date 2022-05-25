<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 권한주기</title>
</head>
<body>
<%@ include file="db_connection.jsp" %>
<%

	request.setCharacterEncoding("UTF-8");

	String u_level = request.getParameter("u_level");

	
	PreparedStatement pstmt = null;
	
	try {
		String sql = "update user set u_level = ? where u_idx ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, u_level);
			
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