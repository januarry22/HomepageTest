package com.test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.BoardDAO;
import com.test.dto.BoardBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		BoardDAO dao = BoardDAO.getInstance();
		
		int num= Integer.parseInt(request.getParameter("num"));	
		// 답글 작성후 원래 페이지로 돌아가기 위해 페이지 번호 필요
		
		String pageNum=request.getParameter("page");
		
		BoardBean board=dao.getDetail(num);
		request.setAttribute("board", board);
		request.setAttribute("page", pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardReplyForm.bo");
		
		return forward;
	}

}
