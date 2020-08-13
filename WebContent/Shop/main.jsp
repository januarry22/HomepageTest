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
    			 value="/Shop/memberlogin.jsp"/>">로그인</a></li>
   	 			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/member/memberJoin.jsp"/>">회원가입</a></li>
    			 </c:when> 
    			 
    			 <c:otherwise>
    			 <li>[<%=id%>]님</li>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/memberlogout.jsp"/>">로그아웃</a></li>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/updateMember.jsp"/>">회원수정</a></li>
    			 </c:otherwise>
    		</c:choose>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/listBoard.jsp"/>">목 록</a>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/insertBoard.jsp"/>">등 록</a>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/updateBoard.jsp"/>">수 정</a>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/deleteBoard.jsp"/>">삭 제</a>
 	
    	</ul>
    	</div>
    	 </div>
    	</nav>