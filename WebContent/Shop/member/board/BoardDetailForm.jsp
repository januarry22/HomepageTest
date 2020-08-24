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
				location.href="BoardUpdateFormAction.bo?num=${board.board_num}&page=${pageNum}";
			}else if(value==3){
				location.href="BoardDeleteAction.bo?num=${board.board_num}";
			}
		}

	var httpRequest = null;

	function getXMLHttpRequest(){
			var httpRequest = null;

			if(window.ActiveXObject){

				try{
					     httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e2) { httpRequest = null; }
            }
        }
        else if(window.XMLHttpRequest){
            httpRequest = new window.XMLHttpRequest();
        }
        return httpRequest;    
    }
    
    // 댓글 등록
    function writeCmt()
    {
        var form = document.getElementById("writeCommentForm");
        
        var board = form.comment_board.value
        var id = form.comment_id.value
        var content = form.comment_content.value;
        
        if(!content)
        {
            alert("내용을 입력하세요.");
            return false;
        }
        else
        {    
            var param="comment_board="+board+"&comment_id="+id+"&comment_content="+content;
                
            httpRequest = getXMLHttpRequest();
            httpRequest.onreadystatechange = checkFunc;
            httpRequest.open("POST", "CommentWriteAction.co", true);    
            httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded;charset=utf-8'); 
            httpRequest.send(param);
        }
    }
    
    function checkFunc(){
        if(httpRequest.readyState == 4){
            // 결과값을 가져온다.
            var resultText = httpRequest.responseText;
            if(resultText == 1){ 
                document.location.reload(); // 상세보기 창 새로고침
            }
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
                <td id="title">글 번호</td>
                <td>${board.board_num}</td>
            </tr>
            <tr>
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
    <br><br>
    
    
    <!-- 댓글 -->
    <div id="comment">
    	<table border="1">
    	    <!-- 댓글 목록-->
    	    <c:if test="${commentList !=null}">
    	    	<c:forEach var="comment" items=${commentList}>
    	    		<tr>
    	    		
                <!-- 아이디, 작성날짜 -->
                <td width="150">
                    <div>
                        ${comment.comment_id}<br>
                        <font size="2" color="lightgray">${comment.comment_date}</font>
                    </div>
                </td>
                
                <!-- 본문내용 -->
                <td width="550">
                    <div class="text_wrapper">
                        ${comment.comment_content}
                    </div>
                </td>
                
                <!-- 버튼 -->
                <td width="100">
                    <div id="btn" style="text-align:center;">
                        <a href="#">[답변]</a><br>
                    <!-- 댓글 작성자만 수정, 삭제 가능하도록 -->    
                    <c:if test="${comment.comment_id == sessionScope.sessionID}">
                        <a href="#">[수정]</a><br>    
                        <a href="#">[삭제]</a>
                    </c:if>        
                    </div>
                </td>
            </tr>
    	    	</c:forEach>
    	    </c:if>
    	    
    	    <!-- 로그인 했을 경우만 댓글 작성가능 -->
            <c:if test="${sessionScope.sessionID !=null}">
            <tr>
            <form id="writeCommentForm">
                <input type="hidden" name="comment_board" value="${board.board_num}">
                <input type="hidden" name="comment_id" value="${sessionScope.sessionID}">
                <!-- 아이디-->
                <td>
                    <div>
                        ${sessionScope.sessionID}
                    </div>
                </td>
                <!-- 본문 작성-->
                <td>
                    <div>
                        <textarea name="comment_content" rows="4" cols="70" ></textarea>
                    </div>
                </td>
                <!-- 댓글 등록 버튼 -->
                <td>
                    <div id="btn" style="text-align:center;">
                        <p><a href="#" onclick="writeCmt()">[댓글등록]</a></p>    
                    </div>
                </td>
            </form>
            </tr>
            </c:if>

    	
    	</table>
    
    
    </div>
    
    

    
</div>    

</body>
</html>