<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function changeView1(){

		location.href="BoardListAction.bo?page=${pageNum}";
		
		}

</script>
</head>
<body>
    <form method="post" action="BoardUpdateAction.bo?page=${pageNum}" name="boardForm" 
            enctype="multipart/form-data">
 	<input type="hidden" name="board_id" value="${sessionScope.sessionID}">
    <input type="hidden" name="board_num" value="${board.board_num}"/>
    <input type="hidden" name="existing_file" value="${board.board_file}"/>
 
    <table border="1">
        <tr>
            <td id="title">작성자</td>
            <td>${sessionScope.sessionID}</td>
        </tr>
            <tr>
            <td id="title">
                제 목
            </td>
            <td>
                <input name="board_subject" type="text" size="70" maxlength="100" 
                    value="${board.board_subject}"/>
            </td>        
        </tr>
        <tr>
            <td id="title">
                내 용
            </td>
            <td>
                <textarea name="board_content" cols="72" rows="20">
                    ${board.board_content}
                </textarea>            
            </td>        
        </tr>
        

        <tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="취소" >
                <input type="submit" value="수정" >
                <input type="button" value="목록" onclick="changeView1()" >            
            </td>
        </tr>
    </table>    
    </form>

</body>
</html>