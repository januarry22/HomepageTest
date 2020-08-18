package com.test.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.dao.BoardDAO;
import com.test.dto.BoardBean;
import com.test.member.controller.Action;
import com.test.member.controller.ActionForward;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ActionForward forward = new ActionForward();
           try {
	            

	            BoardDAO dao = BoardDAO.getInstance();
	            BoardBean borderData = new BoardBean();
	            
	            borderData.setBoard_num(dao.getSeq()); // 시퀀스값 가져와 세팅
	            borderData.setBoard_id(multi.getParameter("board_id")); // 히든값
	            borderData.setBoard_subject(multi.getParameter("board_subject"));
	            borderData.setBoard_content(multi.getParameter("board_content"));
	            borderData.setBoard_file(multi.getParameter("board_file"));
	        
	            boolean result = dao.boardInsert(borderData);
	            
	            if(result){
	                forward.setRedirect(true);
	                forward.setNextPath("BoardListForm.bo");
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("글 작성 오류 : " + e.getMessage());
	        }
	        return forward;


	}

}
