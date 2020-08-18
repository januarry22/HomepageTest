<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
  <%@page import="com.test.dao.memberDAO"%>
    <%@page import="com.test.dto.joinDTO"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>

   <script type="text/javascript">
        
        // 로그아웃 담당 JSP로 이동
        function mainPro(){
            location.href="../main.jsp";
        }
    </script>
</head>
<body>

	
    <jsp:useBean id="member" class="com.test.dto.joinDTO" />
    <jsp:setProperty property="*" name="member"/>    
    
	<%
	request.setCharacterEncoding("utf-8");

	%>
    
    <%
        
        String id= (String)session.getAttribute("sessionID"); 
        member.setId(id);
    
        // 수정할 회원정보를 담고있는 MemberBean을 DAO로 전달하여 회원정보 수정을 한다.
        memberDAO dao = memberDAO.getInstance();
        dao.memberUpdate(member);
    %>
    
    <br><br>
    <font size="5" color="gray">회원정보가 수정되었습니다.</font>
    <br><br>
    <input type="button" value="메인으로" onclick="mainPro()"/>


</body>
</html>