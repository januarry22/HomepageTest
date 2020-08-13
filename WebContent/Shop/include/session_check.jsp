<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% 
	String id=(String)session.getAttribute("id");
	if(id==null) {
%>
	<script>
		alert("로그인한 사용자만 사용가능한 화면입니다.");
		location.href="memberlogin.jsp";
	</script>
<% 
	}
%>