<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script type="text/javascript">
        function ListForm(){
        	location.href="BoardListAction.bo";
        }
    </script>

</head>
<body>
 <form method="post" action="BoardWriteAction.bo" name="boardForm" enctype="multipart/form-data">

	<input type="hidden" name="board_id" value="${sessionScope.sessionID}">
	<table>
	 <tr>
            <td id="title">작성자</td>
            <td>${sessionScope.sessionID}</td>
        </tr>
            <tr>
            <td id="title">제 목
            </td>
            <td>
                <input name="board_subject" type="text" size="70" maxlength="100" value=""/>
            </td>        
        </tr>
        <tr>
            <td id="title">
                내 용
            </td>
            <td>
                <textarea name="board_content" cols="72" rows="20"></textarea>            
            </td>        
        </tr>
        
         <tr>
            <td id="title">
                파일첨부
            </td>
            <td>
                <input type="file" name="board_file" />
            </td>    
        </tr>

        <tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="취소" onclick="ListForm()">
                <input type="submit" value="등록">
                <input type="button" value="목록" onclick="ListForm()">            
            </td>
        </tr>
	
	
	</table> 

	</form>
</body>
</html>