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
	
			<h1><%=session.getAttribute("id") %>�� �α׾ƿ� �Ͻðڽ��ϱ�?</h1>

			<button type="submit" value="�α׾ƿ�">�α׾ƿ�3</button>
		</form>
</body>
</html>