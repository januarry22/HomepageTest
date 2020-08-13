package com.test.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.loginDAO;

/**
 * Servlet implementation class loginCon
 */
@WebServlet("/shopController/*")
public class loginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public loginCon() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 사용자가 요청한 URL
		String url = request.getRequestURL().toString();

		// getRequestURL StringBuffer 로 되어있어서 toString으로 변환해 주어야 한다.
		// 스트링.indexOf("검색어") 검색어를 찾은 위치값, 없으면 -1 리턴
		
		try {
		if (url.indexOf("login.do") != -1) {

			// 폼에서 입력한 값
			String id = request.getParameter("id");
			String passwd1 = request.getParameter("passwd1");
			//System.out.println(id + "," + passwd1);

			loginDAO dao = new loginDAO();
			String name;
		
				name = dao.loginCheck(id, passwd1);

				//System.out.println("이름 : " + name);

				// 로그인 여부
				String message = new String();
				String page = new String();

				if (name != null) {
					message = name + "님 로그인 하셨습니다.";
					page = "/Shop/login_success.jsp";

					// session 객체 인스턴스
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("message", message);
				} else {
					message = "아이디 또는 비밀번호가 일치하지 않습니다.";

					// 로그인 페이지로 돌아감
					page = "/memberlogin.jsp?message=" + URLEncoder.encode(message, "utf-8");
				}

				response.sendRedirect(request.getContextPath() + page);
			} 
		 else if (url.indexOf("logout.do") != -1) {
			// session 객체 만들기
			HttpSession session = request.getSession();

			String page = "/Shop/main.jsp";

			// session을 초기화
			session.invalidate();

			// 로그인 페이지로 되돌아감
			String message = "님 로그아웃 하시겠습니까?";
			response.sendRedirect(request.getContextPath() + page);
		}
		}
		
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
