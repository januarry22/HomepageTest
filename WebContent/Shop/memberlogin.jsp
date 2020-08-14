<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h3>로그인 화면</h3>
	<hr>
	<form method="post" action="<%=request.getContextPath()%>/shopController/login.do">
	<p>아이디 : <input type="text" name="id"><br>
	<p>비밀번호 : <input type="password" name="passwd1" required>
	<hr>
	<input type="submit" value="로그인">
	
	</form>
</body>
</html>