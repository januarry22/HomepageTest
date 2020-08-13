<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

</head>
<body>
		<form method="post" action="<%=request.getContextPath()%>/shopController/logout.do">
	
			<h1><%=session.getAttribute("id") %>님 로그아웃 하시겠습니까?</h1>

			<button type="submit" value="로그아웃">로그아웃3</button>
		</form>
</body>
</html>