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

		// ����ڰ� ��û�� URL
		String url = request.getRequestURL().toString();

		
		try {
		if (url.indexOf("login.do") != -1) {

			
			String id = request.getParameter("id");
			String passwd1 = request.getParameter("passwd1");
			//System.out.println(id + "," + passwd1);

			loginDAO dao = new loginDAO();
			String name;
		
				name = dao.loginCheck(id, passwd1);

	
				String message = new String();
				String page = new String();

				if (name != null) {
					message = name + "님 로그인 하셨습니다.";
					page = "/Shop/login_success.jsp";

					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("message", message);
				} else {
					message = "아이디나 비밀번호가 일치하지 않습니다.";

					page = "/memberlogin.jsp?message=" + URLEncoder.encode(message, "utf-8");
				}

				response.sendRedirect(request.getContextPath() + page);
			} 
		 else if (url.indexOf("logout.do") != -1) {
			// session ��ü �����
	
			HttpSession session = request.getSession();

			String page = "/Shop/main.jsp";

	
			session.invalidate();

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
