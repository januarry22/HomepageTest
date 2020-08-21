package com.test.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.memberDAO;
import com.test.dto.joinDTO;

public class MemberModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8"); // 인코딩

		ActionForward forward = new ActionForward();

		memberDAO dao = memberDAO.getInstance();

		// 세션이 가지고있는 로그인한 ID 정보를 가져온다
		HttpSession session = request.getSession();
		String id = session.getAttribute("sessionID").toString();

		// 수정할 정보를 자바빈에 세팅한다.
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

		dao.memberUpdate(bean);

		forward.setRedirect(true);
		forward.setNextPath("Result.do");

		// 회원정보 수정 성공 메시지를 세션에 담는다.
		session.setAttribute("msg", "0");

		return forward;

	}

}
