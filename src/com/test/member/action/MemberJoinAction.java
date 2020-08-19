package com.test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.memberDAO;
import com.test.dto.joinDTO;

public class MemberJoinAction implements Action {

	@Override
	 public ActionForward execute(HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
	        
	        request.setCharacterEncoding("utf-8"); // 인코딩
	        
	        ActionForward forward = new ActionForward();
	        
	        memberDAO dao = memberDAO.getInstance();
	        
	        // 입력된 정보를 자바빈에 세팅한다.
	        joinDTO bean = new joinDTO();
	        bean.setId(request.getParameter("id"));
	        bean.setPasswd1(request.getParameter("passwd1"));
	        bean.setName(request.getParameter("name"));
	        bean.setTel1(request.getParameter("tel1"));
	        bean.setTel2(request.getParameter("tel2"));
	        bean.setTel3(request.getParameter("tel3"));
	        bean.setAddr(request.getParameter("addr"));
	        bean.setBirth(request.getParameter("birth"));
	        bean.setGender(request.getParameter("gender"));
	      
	        // 회원가입 실행
	        dao.joinMember(bean);
	        
	        // 가입성공
	        forward.setRedirect(true);
	           forward.setNextPath("Result.do");
	        
	           // 가입성공 메시지를 세션에 담는다.
	           request.getSession().setAttribute("msg", "1");
	           
	        return forward;
	    }


}
