<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>

	<script>
		alert("<%=session.getAttribute("message")%>");
		location.href = "main.jsp";
	</script>
</body>
</html>

