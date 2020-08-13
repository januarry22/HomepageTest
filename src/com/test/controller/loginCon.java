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

		// getRequestURL StringBuffer �� �Ǿ��־ toString���� ��ȯ�� �־�� �Ѵ�.
		// ��Ʈ��.indexOf("�˻���") �˻�� ã�� ��ġ��, ������ -1 ����
		
		try {
		if (url.indexOf("login.do") != -1) {

			// ������ �Է��� ��
			String id = request.getParameter("id");
			String passwd1 = request.getParameter("passwd1");
			//System.out.println(id + "," + passwd1);

			loginDAO dao = new loginDAO();
			String name;
		
				name = dao.loginCheck(id, passwd1);

				//System.out.println("�̸� : " + name);

				// �α��� ����
				String message = new String();
				String page = new String();

				if (name != null) {
					message = name + "�� �α��� �ϼ̽��ϴ�.";
					page = "/Shop/login_success.jsp";

					// session ��ü �ν��Ͻ�
					HttpSession session = request.getSession();
					session.setAttribute("id", id);
					session.setAttribute("message", message);
				} else {
					message = "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʽ��ϴ�.";

					// �α��� �������� ���ư�
					page = "/memberlogin.jsp?message=" + URLEncoder.encode(message, "utf-8");
				}

				response.sendRedirect(request.getContextPath() + page);
			} 
		 else if (url.indexOf("logout.do") != -1) {
			// session ��ü �����
			HttpSession session = request.getSession();

			String page = "/Shop/main.jsp";

			// session�� �ʱ�ȭ
			session.invalidate();

			// �α��� �������� �ǵ��ư�
			String message = "�� �α׾ƿ� �Ͻðڽ��ϱ�?";
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
