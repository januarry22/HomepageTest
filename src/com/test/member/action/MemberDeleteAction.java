package com.test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.memberDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 
        ActionForward forward = new ActionForward();
        
        // 세션이 가지고있는 로그인한 ID 정보를 가져온다
        HttpSession session = request.getSession();
        String id = session.getAttribute("sessionID").toString();
        String passwd1 = request.getParameter("passwd1");
        
        memberDAO dao = memberDAO.getInstance();
        int check = dao.memberDelete(id, passwd1);
        
        if(check == 1){
            session.invalidate(); // 회원정보 담긴 세션 삭제
            forward.setRedirect(true);
            forward.setNextPath("Result.do");
        }
        else{
            System.out.println("회원 삭제 실패");
            return null;
        }
        
        return forward;


	}

}
