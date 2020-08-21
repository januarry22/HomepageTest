package com.test.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.BoardDAO;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

public class BoardDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward= new ActionForward();
		
		String num=request.getParameter("num");
		int boardNum=Integer.parseInt(num);
		
		BoardDAO dao=BoardDAO.getInstance();
		
	//	String fileName=dao.getFileName(boardNum);
		boolean result=dao.deleteBoard(boardNum);
		
//        if(fileName != null)
//        {
//            // 파일이 있는 폴더의 절대경로를 가져온다.
//            String folder = request.getServletContext().getRealPath("UploadFolder");
//            // 파일의 절대경로를 만든다.
//            String filePath = folder + "/" + fileName;
// 
//            File file = new File(filePath);
//            if(file.exists()) file.delete(); // 파일은 1개만 업로드 되므로 한번만 삭제하면 된다.
		
		if(result) {
			forward.setRedirect(true);
			forward.setNextPath("BoardListAction.bo");
		}

		else
		return null;
		
		return forward;
	}

}
