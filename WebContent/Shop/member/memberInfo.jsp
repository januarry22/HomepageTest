<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
    <%@page import="com.test.dao.memberDAO"%>
    <%@page import="com.test.dto.joinDTO"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script type="text/javascript">
    
        function changeForm(val){
            if(val == "-1"){
                location.href="MainForm.do";
            }else if(val == "0"){
                location.href="MemberModifyFormAction.do";
            }else if(val == "1"){
                location.href="DeleteForm.do";
            }
        }
        
    </script>


</head>
<body>


	<%
	request.setCharacterEncoding("utf-8");

	%>
	<%--
		String id = (String)session.getAttribute("sessionID").toString();
		memberDAO dao=memberDAO.getInstance();
		joinDTO member=dao.getUserInfo(id);
		
	
	--%>

	  <c:set var="member" value="${requestScope.memberInfo}"/>

           <table border="1">
            <tr>
                <td id="title">아이디</td>
                <td>${member.id}</td>
            </tr>
                        
            <tr>
                <td id="title">비밀번호</td>
                <td>${member.passwd1}</td>
            </tr>
                    
            <tr>
                <td id="title">이름</td>
                <td>${member.name}</td>
            </tr>
                    
            <tr>
                <td id="title">성별</td>
                <td>${member.gender}</td>
            </tr>
                    
            <tr>
                <td id="title">생일</td>
                <td>
                    ${member.birth} 
                </td>
            </tr>
                   
                    
            <tr>
                <td id="title">휴대전화</td>
                <td>${member.tel1}-${member.tel2}-${member.tel3}</td>
            </tr>
            <tr>
                <td id="title">주소</td>
                <td>
                    ${member.addr}
                </td>
            </tr>
        </table>

            
            <input type="button" value="뒤로" onclick="changeForm(-1)">
	        <input type="button" value="수정" onclick="changeForm(0)">
	        <input type="button" value="삭제" onclick="changeForm(1)">
	
	

</body>
</html>