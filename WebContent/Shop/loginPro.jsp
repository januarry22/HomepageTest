<%@page import="com.test.dao.loginDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ page import="com.test.dto.joinDTO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%@ include file="member/dbconn.jsp" %>
<%
	request.setCharacterEncoding("UTF-8");

	String lg_id = request.getParameter("lg_id");
	String lg_passwd = request.getParameter("lg_passwd1");

	int ok=0;
	PreparedStatement pstmt= null;
	ResultSet rs = null;
	
	
	try{
		
		String sql="SELECT id, passwd1 FROM shop where id=?, passwd1=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, lg_id);
		pstmt.setString(2, lg_passwd);
		rs=pstmt.executeQuery();
		
		if(rs.next()){
			if(rs.getString("id").equals(lg_id)&&rs.getString("passwd1").equals(lg_passwd)){
				System.out.println("로그인 성공");
				ok=1;
			}else{
				System.out.println("비밀번호 불일치");
				ok=2;
			}
		}else{
			System.out.println("아이디 불일치");
				ok=3;
		}

	}catch(Exception e){
		
	}finally{
		if(pstmt!=null){
			pstmt.close();
		}
		if(conn!=null){
			conn.close();
		}
		if(rs!=null){
			rs.close();
		}
	}
	
	

%>

</body>
</html>