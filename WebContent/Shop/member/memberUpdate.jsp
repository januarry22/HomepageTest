<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="java.sql.*"%>
   <%@page import="com.test.dto.joinDTO" %>
     <%@page import="com.test.dao.memberDAO"%>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		String id = session.getAttribute("sessionID").toString();
	
		memberDAO dao=memberDAO.getInstance();
		joinDTO member=dao.getUserInfo(id);
		
	
	--%>
    <c:set var="member" value="${requestScope.memberInfo}"/>
        
        <!-- 입력한 값을 전송하기 위해 form 태그를 사용한다 -->
        <!-- 값(파라미터) 전송은 POST 방식 -->
        <form method="post" action="MemberModifyAction.do" 
                name="userInfo" onsubmit="return checkValue()">
                
            <table>
                <tr>
                    <td id="title">아이디</td>
                    <td id="title">${member.id}</td>
                </tr>
                <tr>
                    <td id="title">비밀번호</td>
                    <td>
                        <input type="password" name="passwd1" maxlength="50" 
                            value="${member.passwd1}">
                    </td>
                </tr>
            </table>    
            <br><br>    
            <table>
 
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
                    <td>
                        <input type="text" name="phone" value="${member.tel1}"/>
                    </td>
                </tr>
                <tr>
                    <td id="title">주소</td>
                    <td>
                        <input type="text" size="50" name="address"
                            value="${member.addr}"/>
                    </td>
                </tr>
            </table>
            <br><br>
            <input type="button" value="취소" onclick="javascript:window.location='MainForm.do'">
            <input type="submit" value="수정"/>  

	</form>

</body>
</html>