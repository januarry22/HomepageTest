<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
    <br><br>
    <div id="board">
        <table id="detailBoard">
        
            <tr>
                <td id="title">작성일</td>
                <td>date</td>
            </tr>
            <tr>
                <td id="title">작성자</td>
                <td>${board.board_id}</td>
            </tr>
            <tr>
                <td id="title">
                    제 목
                </td>
                <td>
                    ${board.board_subject}
                </td>        
            </tr>
            <tr>
                <td id="title">
                    내 용
                </td>
                <td>
                    ${board.board_content}
                </td>        
            </tr>
          
    
            <tr align="center" valign="middle">
                <td colspan="5">
                    <input type="button" value="수정" >
                    <input type="button" value="삭제" >
                    <input type="button" value=답글 >    
                    <input type="button" value="목록" 
                        onclick="javascript:location.href='BoardListAction.bo?page=${pageNum}'">            
                </td>
            </tr>
        </table>
    </div>
</div>    

</body>
</html>