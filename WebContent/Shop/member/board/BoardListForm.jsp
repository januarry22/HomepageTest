<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   <script type="text/javascript">
        function writeForm(){
        	location.href="BoardWriteAction.bo";
        }
    </script>


</head>
<body>
<div id="wrap">
    <br>
    <div id="topForm">
        <c:if test="${sessionScope.sessionID!=null}">
            <input type="button" value="글쓰기" onclick="writeForm()">
        </c:if>    
           
    </div>
    <!-- 게시글 목록 부분 -->
    <br>
    <div id="board">
       <table>
            <tr height="30">
                <td>글번호</td>
                <td>제목</td>
                <td>작성자</td>
                <td>작성일</td>
                <td>조회수</td>
            </tr>    
			<c:forEach var="board" items="${list}">
       			 <tr>
                <td>${board.board_num}</td>
                <td align="left">
                	<c:if test="${board.re_lev > 0}">
                		<c:forEach begin="1" end="${board.re_lev}">
                			&nbsp;&nbsp;
                		</c:forEach>
                		RE : 
                	</c:if>
                	<a href="BoardDetailAction.bo?num=${board.board_num}&pageNum=${spage}">
                   ${board.board_subject}
                 </a>
                </td>
                <td>
                    ${board.board_id}
                </td>
                <td>date</td>
                <td>${board.hit}</td>
            </tr>
           	 </c:forEach>
        	</table>
   		</div>
    
    <!-- 페이지 넘버 부분-->
    <br>
    <div id="pageForm">
        <c:if test="${startPage != 1}">
            <a href='BoardListAction.bo?page=${startPage-1}'>[이전]</a>
        </c:if>
        
        <c:forEach var="pageNum" begin="${startPage}" end="${endPage}">
            <c:if test="${pageNum == spage}">
                ${pageNum}&nbsp;
            </c:if>
            <c:if test="${pageNum != spage}">
                <a href='BoardListAction.bo?page=${pageNum}'>${pageNum}&nbsp;</a>
            </c:if>
        </c:forEach>
        
        <c:if test="${endPage != maxPage }">
            <a href='BoardListAction.bo?page=${endPage+1}'>[다음]</a>
        </c:if>
    </div>
     
    <!--  검색 부분 -->
    <br>
    <div id="searchForm">
        <form>
            <select name="opt">
                <option value="0">제목</option>
                <option value="1">내용</option>
                <option value="2">제목+내용</option>
                <option value="3">글쓴이</option>
            </select>
            <input type="text" size="20" name="condition"/>&nbsp;
            <input type="submit" value="검색"/>
        </form>    
    </div>
</div>    


</body>
</html>