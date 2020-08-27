<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <br>
    <b><font size="6" color="gray">답글 작성</font></b>
    <br>
    
    <form method="post" action="BoardReplyAction.bo?page=${page}" name="boardForm">
    
    <input type="hidden" name="board_id" value="${sessionScope.sessionID}">
    <input type="hidden" name="board_num" value="${board.board_num}"/>
    <input type="hidden" name="re_ref" value="${board.re_ref}"/>
    <input type="hidden" name="re_lev" value="${board.re_lev}"/>
    <input type="hidden" name="re_seq" value="${board.re_seq}"/>
 
    <table border="1">
        <tr>
            <td id="title">작성자</td>
            <td>${sessionScope.sessionID}</td>
        </tr>
  		 <tr>
            <td id="title">
               글번호 </td>
               <td>${board.board_num}</td>
            <tr>
            <td id="title">
                제 목
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
                <textarea name="board_content" cols="72" rows="20">
                </textarea>            
            </td>        
        </tr>
 
        <tr align="center" valign="middle">
            <td colspan="5">
                <input type="reset" value="작성취소" >
                <input type="submit" value="등록" >
                <input type="button" value="목록" onclick="javascript:history.go(-1)">            
            </td>
        </tr>
    </table>    
    </form>

</body>
</html>