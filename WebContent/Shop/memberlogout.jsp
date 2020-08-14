<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body>
		<form method="post" action="<%=request.getContextPath()%>/shopController/logout.do">
	
			<h1><%=session.getAttribute("name") %>님 로그아웃 하시겠습니까?</h1>

			<button type="submit" onclick="btn_submit()" value="로그아웃">로그아웃</button>
		</form>
</body>
</html>