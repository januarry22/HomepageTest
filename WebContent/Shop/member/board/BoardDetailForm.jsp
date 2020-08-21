<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function changeV(value){
			if (value==0){
					location.href="BoardListAction.bo?page=${pageNum}";
				}
			else if(value==1){
					location.href="BoardReplyFormAction.bo?num=${board.board_num}&page=${pageNum}";
				}
			else if(value==2){
				location.href="BoardUpdateAction.bo?num=${board.board_num}";
			}else if(value==3){
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
			}
		}

</script>



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
                <c:if test="${sessionScope.sessionID!=null}">
                <c:if test="${sessionScope.sessionID==board.board_id}">
                   <input type="button" value="수정" onclick="changeV(2)">
                    <input type="button" value="삭제" onclick="changeV(3)">
                 </c:if>   
                    <input type="button" value="답글" onclick="changeV(1)">    
                 </c:if>
                    <input type="button" value="목록" onclick="changeV(0)">            
                </td>
            </tr>
        </table>
    </div>
</div>    

</body>
</html>