package com.test.board.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.dao.BoardDAO;
import com.test.dao.memberDAO;
import com.test.dto.BoardBean;
import com.test.dto.joinDTO;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class BoardWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		 ActionForward forward = new ActionForward();
         
		 	request.setCharacterEncoding("utf-8"); // 인코딩
	        
	        // 업로드 파일 사이즈
	        int fileSize= 5*1024*1024;
	        // 업로드될 폴더 경로
	    
	        try {
	            
	            // 파일업로드 
	        	MultipartRequest multi = new MultipartRequest(request, "C:\\upload", fileSize, "utf-8", new DefaultFileRenamePolicy());

	            // 파일이름 가져오기
	            String fileName = "";
	            Enumeration<String> names = multi.getFileNames();
	            if(names.hasMoreElements())
	            {
	                String name = names.nextElement();
	                fileName = multi.getFilesystemName(name);
	            }
	            
	            BoardDAO dao = BoardDAO.getInstance();
	            BoardBean borderData = new BoardBean();
	            
	   //         borderData.setBoard_num(dao.getSeq()); // 시퀀스값 가져와 세팅
	            borderData.setBoard_id(multi.getParameter("board_id")); // 히든값
	            borderData.setBoard_subject(multi.getParameter("board_subject"));
	            borderData.setBoard_content(multi.getParameter("board_content"));
	            borderData.setBoard_file(multi.getParameter("board_file"));
	        
	            boolean result = dao.boardInsert(borderData);
	            //dao에서 boardInsert메소드 사용해 새로운 값 넣음
	            
	            if(result){
	                forward.setRedirect(true);
	                forward.setNextPath("BoardListAction.bo");
	            }
	            
//	            forward.setRedirect(true);
//		        forward.setNextPath("../BoardListAction.bo");
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("글 작성 오류 : " + e.getMessage());
	        }
	        return forward;

//			request.setCharacterEncoding("utf-8"); // 인코딩
//	        
//	        ActionForward forward = new ActionForward();
//	        
//	        BoardDAO dao = BoardDAO.getInstance();
//	        
//	        // 입력된 정보를 자바빈에 세팅한다.
//	        BoardBean borderData = new BoardBean();
//	        
//	        borderData.setBoard_num(dao.getSeq());
//	        borderData.setBoard_content(request.getParameter("board_content"));
//	        borderData.setBoard_id(request.getParameter("board_id"));
//	        borderData.setBoard_subject(request.getParameter("board_subject"));
//	 
//	        // 회원가입 실행
//	        boolean result = dao.boardInsert(borderData);
//	        
//            if(result){
//            forward.setRedirect(true);
//            forward.setNextPath("BoardListForm.bo");
//        }
//	           
//	        return forward;
	}

}
