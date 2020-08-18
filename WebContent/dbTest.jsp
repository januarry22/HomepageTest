<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*" %>
    <%@ page import="com.test.dbconn.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String sql="select id from shop";
Connection conn=DBConnection.getConnection();
PreparedStatement pstmt=conn.prepareStatement(sql);
ResultSet rs=pstmt.executeQuery();
try{
	

while(rs.next()){
	out.println("<tr>");
	out.println(rs.getString("id"));
}
}catch(Exception e){
	e.printStackTrace();
}finally{
	if(pstmt!=null){
		pstmt.close();
	}
	if(conn!=null){
		conn.close();
	}if(rs!=null){
		rs.close();
	}
}


%>
</body>
</html>