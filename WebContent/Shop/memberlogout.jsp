<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
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
	
			<h1><%=session.getAttribute("name") %>�� �α׾ƿ� �Ͻðڽ��ϱ�?</h1>

			<button type="submit" onclick="btn_submit()" value="�α׾ƿ�">�α׾ƿ�</button>
		</form>
</body>
</html>