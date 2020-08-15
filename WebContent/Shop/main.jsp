<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script type="text/javascript">
        
        // 로그아웃 담당 JSP로 이동
        function logoutPro(){
            location.href="member/pro/LogoutPro.jsp";
        }
    </script>

</head>
<body>
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
    			<c:when test="${empty sessionID}">
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/memberlogin.jsp"/>">로그인</a></li>
   	 			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/member/memberJoin.jsp"/>">회원가입</a></li>
    			 </c:when> 
    			 
    			 <c:otherwise>
    			 <li>[<%=session.getAttribute("sessionID")%>]님</li>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/memberlogout.jsp"/>">로그아웃</a></li>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/member/memberInfo.jsp"/>">회원정보</a></li>
    			 </c:otherwise>
    		</c:choose>
    			<li class=""><a class="nav-link" href="<c:url
    			 value="/Shop/listBoard.jsp"/>">게시판</a>
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
    	
    	</body>
</html>