<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="dbconn.jsp" %>

	<%
	request.setCharacterEncoding("utf-8");
	
	String id=request.getParameter("id");
	String passwd1=request.getParameter("passwd1");
	String name=request.getParameter("name");
	String gender=request.getParameter("gender");
	Integer tel1=Integer.parseInt(request.getParameter("tel1"));
	Integer tel2=Integer.parseInt(request.getParameter("tel2"));
	Integer tel3=Integer.parseInt(request.getParameter("tel3"));
	String addr= request.getParameter("addr");
	String birth= request.getParameter("birth");
	
	PreparedStatement pstmt=null;
	
	try{
	
		String sql="INSERT INTO shop(id, name, passwd1, tel1, tel2, tel3, addr, gender, birth) VALUES (?,?,?,?,?,?,?,?,?)";
		pstmt= conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, name);
		pstmt.setString(3, passwd1);
		pstmt.setInt(4, tel1);
		pstmt.setInt(5, tel2);
		pstmt.setInt(6, tel3);
		pstmt.setString(7, addr);
		pstmt.setString(8, gender);
		pstmt.setString(9, birth);
		pstmt.executeUpdate();
		out.println("회원정보 등록");
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		if(pstmt!=null){
			pstmt.close();
		}
		if(conn!=null){
			conn.close();
		}
	}
	%>
	


</body>
</html>