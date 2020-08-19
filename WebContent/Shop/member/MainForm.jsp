<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    <%--
        String contentPage=request.getParameter("contentPage");
        if(contentPage==null)
            contentPage="FirstView.jsp";
            
    --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
        <div id="header">
            <jsp:include page="pages/Header.jsp" />
        </div>
        <div id="main">
        
            <!-- contentPage가 없을 경우 FirstView.jsp를 보여준다. -->
            <c:set var="contentPage" value="${param.contentPage}"/>
            <c:if test="${contentPage==null}">    
                <jsp:include page="pages/FirstView.jsp" />
            </c:if>
            <jsp:include page="${contentPage}" />
            
        </div>
        <div id="footer"> 
            <jsp:include page="pages/Footer.jsp" />
        </div>
    </div>


</body>
</html>