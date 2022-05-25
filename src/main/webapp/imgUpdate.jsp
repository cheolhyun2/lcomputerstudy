<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    // 실제로 서버에 저장되는 path
    //String path = request.getRealPath("img");
	String path = "C:\\Users\\l8-morning\\Documents\\work10\\lcomputerstudy\\src\\main\\webapp\\img";
    out.println("절대 경로 : " + path + "<br/>");
    
    int size = 1024 * 1024 * 10; 
    String fileName = "";    
    String originalFileName = "";   
    

    try{
    
        MultipartRequest multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
        
        // 전송한 전체 파일이름들을 가져온다.
        Enumeration files = multi.getFileNames();
        String str = (String)files.nextElement();
        
       
        fileName = multi.getFilesystemName(str);
        originalFileName = multi.getOriginalFileName(str);
        
    }catch(Exception e){
        e.printStackTrace();
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>업로드결과</title>
</head>
<body>
<h1>업로드결과</h1>
<hr>
<h2><span>업로드완료!!</span></h2>
</body>
</html>


