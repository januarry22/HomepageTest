package com.test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.BoardDAO;
import com.test.dto.BoardBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class BoardDetailAction implements Action{

	@Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        // 파라미터로 넘어온 글번호를 가져온다.
        String num = request.getParameter("num");
        int boardNum = Integer.parseInt(num);
        
  //      String pageNum = request.getParameter("pageNum");
        
        BoardDAO dao = BoardDAO.getInstance();
        BoardBean board = dao.getDetail(boardNum);
        boolean result = dao.updateCount(boardNum);
        
        request.setAttribute("board", board);
     //   request.setAttribute("pageNum", pageNum);
        
        if(result){
            forward.setRedirect(false); // 단순한 조회이므로
            forward.setNextPath("/Shop/member/board/BoardDetailForm.jsp");
        }
        
        return forward;
    }

}
