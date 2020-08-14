<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <%
    	String id=(String)session.getAttribute("id");
    
    %>
    
    <nav class="">
    
    <div class="container">
    	<div class="">
    		<a class="" href="./main.jsp">Home</a>
    	</div>
    	
    <div>
    	<ul class="">
    		<c:choose>
    			<c:when test="${empty id}">
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/memberlogin.jsp"/>">�α���</a></li>
   	 			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/member/memberJoin.jsp"/>">ȸ������</a></li>
    			 </c:when> 
    			 
    			 <c:otherwise>
    			 <li>[<%=id%>]��</li>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/memberlogout.jsp"/>">�α׾ƿ�</a></li>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/member/memberInfo.jsp"/>">ȸ������</a></li>
    			 </c:otherwise>
    		</c:choose>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/listBoard.jsp"/>">�Խ���</a>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/insertBoard.jsp"/>">�� ��</a>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/updateBoard.jsp"/>">�� ��</a>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/deleteBoard.jsp"/>">�� ��</a>
 	
    	</ul>
    	</div>
    	 </div>
    	</nav>