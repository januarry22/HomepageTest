package com.test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.BoardDAO;
import com.test.dto.BoardBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class BoardUpdateFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		
		String pageNum=request.getParameter("page");
		String num= request.getParameter("num");
		int boardNum=Integer.parseInt(num);
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean board = dao.getDetail(boardNum);
		
		request.setAttribute("board", board);
		request.setAttribute("pageNum", pageNum);
		
		forward.setRedirect(false);
		forward.setNextPath("BoardUpdateForm.bo");
	
		return forward;
	}

	
}
