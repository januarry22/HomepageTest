<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="java.sql.*"%>
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
		String id = session.getAttribute("sessionID").toString();
	
		memberDAO dao=memberDAO.getInstance();
		joinDTO member=dao.getUserInfo(id);
		
	
	%>
  <form method="post" action="memberUpdatePro.jsp" name="userInfo">

		<table border="1">
		<tr>
			<td id="title">아이디</td>
			<td><%=member.getId()%></td>
		</tr>
		<tr>
			<td id="title">비밀번호</td>
			<td><input type="password" name="passwd1"></td>
		</tr>
		
		
		<tr>
			<td id="title">이 름</td>
			<td><input type="text" name="name"></td>
		</tr>
		
		<tr>
			<td>성별</td>
			<td><input type="radio" name="gender" value="남">남
			<input type="radio" name="gender" value="여">여</td>
		</tr>
		
		<tr>
			<td id="title">생년월일</td>
			<td><input type="text" name="birth">
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
			<td id="title">주소</td>
			<td><input type="text" name="addr"></td>
		</tr>
		</table>
		
		<input type="submit" value="회원 수정">
		<input type="reset" value="취소" onclick="javascript:window.location='../main.jsp'">
	</form>

</body>
</html>