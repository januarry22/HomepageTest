<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
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