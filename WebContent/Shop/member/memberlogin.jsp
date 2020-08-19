<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <script type="text/javascript">
 
        function checkValue()
        {
            inputForm = eval("document.loginInfo");
            if(!inputForm.id.value)
            {
                alert("아이디를 입력하세요");    
                inputForm.id.focus();
                return false;
            }
            if(!inputForm.password.value)
            {
                alert("비밀번호를 입력하세요");    
                inputForm.password.focus();
                return false;
            }
        }
    
        // 취소 버튼 클릭시 첫화면으로 이동
        function goFirstForm() {
            location.href="MainForm.do";
        }
    </script>



</head>
<body>

 <div id="wrap">
    
	        <form name="loginInfo" method="post" action="MemberLoginAction.do" onsubmit="return checkValue()">
	<p>아이디 : <input type="text" name="id"><br>
	<p>비밀번호 : <input type="password" name="passwd1" required>
	<hr>
	<input type="submit" value="로그인" />
		<input type="button" value="취소" onclick="goFirstForm()"/>
	</form>
	
	
	
	<%--
            // 아이디, 비밀번호가 틀릴경우 화면에 메시지 표시
            // LoginPro.jsp에서 로그인 처리 결과에 따른 메시지를 보낸다.
            String msg=request.getParameter("msg");
            
            if(msg!=null && msg.equals("0")) 
            {
                out.println("<br>");
                out.println("<font color='red' size='5'>비밀번호를 확인해 주세요.</font>");
            }
            else if(msg!=null && msg.equals("-1"))
            {    
                out.println("<br>");
                out.println("<font color='red' size='5'>아이디를 확인해 주세요.</font>");
            }
        --%>    
 		
 		
 		<c:set var="failMessage" value="${requestScope.fail}"/>
        <c:if test="${failMessage!=null}">    
            <c:choose>
                <c:when test="${failMessage=='0'}">
                    <br><font color='red' size='5'>비밀번호를 확인해 주세요.</font>
                </c:when>
                <c:otherwise>
                    <br><font color='red' size='5'>아이디를 확인해 주세요.</font>
                </c:otherwise>
            </c:choose>
        </c:if>
        </div>


</body>
</html>