<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script type="text/javascript">
        // 비밀번호 미입력시 경고창
        function checkValue(){
            if(!document.memberDelete.password.value){
                alert("비밀번호를 입력하지 않았습니다.");
                return false;
            }
        }

        function mainPro(){
            location.href="../main.jsp";
        }
    </script>

</head>
<body>
  <form name="memberDelete" method="post" action="memberDeletepro.jsp"
        onsubmit="return checkValue()">
 
        <table>

            <tr>
                <td bgcolor="skyblue">비밀번호</td>
                <td><input type="password" name="passwd1" maxlength="50"></td>
            </tr>
        </table>
        
        <br> 
        <input type="button" value="취소" onclick="mainPro()">
        <input type="submit" value="탈퇴" /> 
    </form>


</body>
</html>