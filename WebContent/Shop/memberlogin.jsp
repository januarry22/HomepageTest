<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>


	<h3>�α��� ȭ��</h3>
	<hr>
	<form method="post" action="<%=request.getContextPath()%>/shopController/login.do">
	<p>���̵� : <input type="text" name="id"><br>
	<p>��й�ȣ : <input type="password" name="passwd1" required>
	<hr>
	<input type="submit" value="�α���">
	
	</form>
</body>
</html>