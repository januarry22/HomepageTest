<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%>
    <%@page import="com.test.dao.memberDAO"%>
    <%@page import="com.test.dto.joinDTO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function changeForm(val){

		if(val=="-1"){
				location.href="main.jsp";
		}else if(val=="0"){
			location.href="main.jsp?contentPage=Shop/member/memberUpdate.jsp";
		}else if(val=="1"){
			location.href="main.jsp?contentPage=Shop/member/memberDelete.jsp";
		}

		}

</script>

</head>
<body>

	<%
		String id = session.getAttribute("sessionId").toString();
	
		memberDAO dao=memberDAO.getInstance();
		joinDTO member=dao.getUserInfo(id);
		
	
	%>
	<table border="1">
                <tr>
                    <td>아이디</td>
                    <td><%=member.getId()%></td>
                </tr>
                <tr>
                    <td>비밀번호</td>
                    <td><%=member.getPasswd1()%></td>
                </tr>
                                <tr>
                    <td>이름</td>
                    <td><%=member.getName()%></td>
                </tr>
                <tr>
                    <td>성별</td>
                    <td><%=member.getGender()%></td>
                </tr>
                        <tr>
                    <td>생년월일</td>
                    <td><%=member.getBirth()%></td>
                </tr>

                <tr>
                    <td>전화</td>
                    <td><%=member.getTel1()%>-<%=member.getTel2()%>-<%=member.getTel3()%></td>
                </tr>
                <tr>
                    <td>주소</td>
                    <td><%=member.getAddr()%></td>
                </tr>
            </table>
            
            <input type="button" value="뒤로" onclick="changeForm(-1)">
	        <input type="button" value="수정" onclick="changeForm(0)">
	        <input type="button" value="삭제" onclick="changeForm(1)">
	
	
	

</body>
</html>