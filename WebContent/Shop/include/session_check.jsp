<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	String id=(String)session.getAttribute("id");
	if(id==null) {
%>
	<script>
		alert("�α����� ����ڸ� ��밡���� ȭ���Դϴ�.");
		location.href="memberlogin.jsp";
	</script>
<% 
	}
%>