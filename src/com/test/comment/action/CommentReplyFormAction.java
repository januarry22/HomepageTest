package com.test.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.CommentDAO;
import com.test.dto.CommentBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class CommentReplyFormAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		ActionForward forward=new ActionForward();
		
		int comment_num=Integer.parseInt(request.getParameter("num"));
	
		CommentDAO dao = CommentDAO.getInstance();
		CommentBean comment=dao.getComment(comment_num);
	
	
		request.setAttribute("comment", comment);
	
		
		forward.setRedirect(false);
		forward.setNextPath("../member/board/CommentReplyFrom.jsp");
		
		
		
		return forward;
	}

}
