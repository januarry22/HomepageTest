package com.test.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.CommentDAO;
import com.test.dto.CommentBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class CommentWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CommentDAO dao= CommentDAO.getInstance();
		CommentBean comment= new CommentBean();
		
	//	int comment_num= Integer.parseInt(request.getParameter("comment_num"));
		int comment_board = Integer.parseInt(request.getParameter("comment_board"));
		String comment_id= request.getParameter("comment_id");
		String comment_content=request.getParameter("comment_content");
		
	//	comment.setComment_num(comment_num);
		comment.setComment_board(comment_board);
		comment.setComment_id(comment_id);
		comment.setComment_content(comment_content);
		
		boolean result = dao.insertComment(comment);
		
		if(result) {
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out= response.getWriter();
			out.println("1");
			out.close();
		}
		
		return null;
	}

}
