package com.test.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.CommentDAO;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class CommentDeleteAction implements Action
{
   @Override
   public ActionForward execute(HttpServletRequest request,
           HttpServletResponse response) throws Exception {
   
       int comment_num = Integer.parseInt(request.getParameter("comment_num"));
       
       CommentDAO dao = CommentDAO.getInstance();
       boolean result = dao.deleteComment(comment_num);
       
       response.setContentType("text/html;charset=euc-kr");
       PrintWriter out = response.getWriter();

       // 정상적으로 댓글을 삭제했을경우 1을 전달한다.
       if(result) out.println("1");
       
       out.close();
       return null;
   }
}
