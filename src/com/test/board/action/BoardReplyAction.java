package com.test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.BoardDAO;
import com.test.dto.BoardBean;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class BoardReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardBean bean= new BoardBean();
		
		String pageNum= request.getParameter("page");
		
	//	int num=Integer.parseInt(request.getParameter("board_num"));
		String id= request.getParameter("board_id");
		String subject= request.getParameter("board_subject");
		String content= request.getParameter("board_content");
		int ref= Integer.parseInt(request.getParameter("re_ref"));
		int lev= Integer.parseInt(request.getParameter("re_lev"));
		int seq= Integer.parseInt(request.getParameter("re_seq"));
		
		
		// 답글 중 가장 최근 답글이 위로 올라가게 처리
		// 답글의 순서인 seq를 1증가 시킴
		bean.setRe_ref(ref);
		bean.setRe_seq(seq);
		dao.updateReSeq(bean);

//		bean.setBoard_num(num);
		bean.setBoard_id(id);
		bean.setBoard_subject(subject);
		bean.setBoard_content(content);
		bean.setRe_ref(ref);
		bean.setRe_lev(lev+1);
		bean.setRe_seq(seq+1);
		
		boolean result=dao.boardInsert(bean);
		
		if(result) {
			forward.setRedirect(false);
			forward.setNextPath("BoardListAction.bo?=page"+pageNum);
		}
		
		
		return forward;
	}

}
