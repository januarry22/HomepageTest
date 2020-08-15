<%@page import="com.test.dao.memberDAO"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="com.test.dto.joinDTO" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>

	<%
	request.setCharacterEncoding("utf-8");

	%>
	
	<jsp:useBean id="member" class="com.test.dto.joinDTO"/>
	<jsp:setProperty property="*" name="member"/>
	
	<%
		memberDAO dao = memberDAO.getInstance();
		
		dao.joinMember(member);
		
	%>
	  
        <b>회원가입이 되었습니다.</b><br>
        
        <b><%=member.getId() %></b>님의 회원정보입니다. <br/><br/>
        
        <table border="1">
                <tr>
                    <td>아이디</td>
                    <td><%=member.getId()%></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><%=member.getPasswd1()%></td>
                </tr>
                                <tr>
                    <td>이름</td>
                    <td><%=member.getName()%></td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td><%=member.getGender()%></td>
                </tr>
                        <tr>
                    <td>생년월일</td>
                    <td><%=member.getBirth()%></td>
                </tr>

                <tr>
                    <td>전화</td>
                    <td><%=member.getTel1()%>-<%=member.getTel2()%>-<%=member.getTel3()%></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><%=member.getAddr()%></td>
                </tr>
            </table>
   
</body>
</html>