<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.test.dto.joinDTO" %>
     <%@page import="com.test.dao.memberDAO"%>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		String id = session.getAttribute("sessionId").toString();
	
		memberDAO dao=memberDAO.getInstance();
		joinDTO member=dao.getUserInfo(id);
		
	
	%>
  <form method="post" action="main.jsp?contentPage=Shop/member/memberUpdatepro.jsp" 
                name="userInfo" onsubmit="return checkValue()">

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
			<td>비밀번호 확인</td>
			<td>
			<input type="password" name="passwd2">
			<input type="button" value="비밀번호확인" onclick=""></td>
		</tr>
		
		<tr>
			<td>이 름</td>
			<td><%=member.getName()%></td>
		</tr>
		
		<tr>
			<td>성별</td>
			<td><%=member.getGender()%></td>
		</tr>
		
		<tr>
			<td>생년월일</td>
			<td><%=member.getBirth()%>
			</td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td><select name="tel1">
					<option value="010" selected>010</option>
					<option value="010">011</option>
					<option value="010">012</option>
			</select>-
			<input type="text" name="tel2">-
			<input type="text" name="tel3"> </td>
		</tr>
		
		<tr>
			<td>주소</td>
			<td><%=member.getAddr()%></td>
		</tr>
		</table>
		
		<input type="submit" value="회원 수정">
		<input type="reset" value="취소" onclick="javascript:window.location='main.jsp'">
	</form>

</body>
</html>