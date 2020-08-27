package com.test.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.CommentDAO;
import com.test.dto.CommentBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class CommentReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

        CommentDAO dao = CommentDAO.getInstance();
        
		int comment_num = Integer.parseInt(request.getParameter("comment_num"));
        int comment_board = Integer.parseInt(request.getParameter("comment_board"));
        
        String comment_id = request.getParameter("comment_id");
        String comment_content = request.getParameter("comment_content");
 
        
        CommentBean comment = new CommentBean();    
    	
      
     //   comment.setComment_num(comment_num);    // 시퀀스를 가져와 세팅한다
        comment.setComment_board(comment_board);
        comment.setComment_id(comment_id);
        comment.setComment_content(comment_content);
        comment.setComment_parent(comment_num);  // 부모댓글의 글번호를 세팅
        
        boolean result = dao.insertComment(comment);
        
        
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        
        // 정상적으로 댓글을 저장했을경우 1을 전달한다.
        if(result) out.println("1");
        else out.println("0");
        
        out.close();
		return null;

	}

}
