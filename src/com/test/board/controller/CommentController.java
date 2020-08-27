package com.test.board.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;

import com.test.member.action.Action;
import com.test.member.action.ActionForward;

/**
 * Servlet implementation class CommentController
 */
@WebServlet("*.co")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashedMap<String, Action> commandMap;
       

	
	public void init(ServletConfig config) throws ServletException{
		loadProperties("com/test/board/properties/CommentCommand");
	}

	private void loadProperties(String filePath) {
		// TODO Auto-generated method stub
		commandMap = new HashedMap<String, Action>();
		
		ResourceBundle rb = ResourceBundle.getBundle(filePath);
		Enumeration<String> actionEnum = rb.getKeys();
		
		while(actionEnum.hasMoreElements()) {
			String command= actionEnum.nextElement();
			String className=rb.getString(command);
	
			try {
				Class actionClass=Class.forName(className);
				Action actionInstance = (Action)actionClass.newInstance();
				
				commandMap.put(command, actionInstance);
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		}
	}
	
    private void doProcess(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		int cmdIdx=requestURI.lastIndexOf("/")+1;
		String command = requestURI.substring(cmdIdx);
		
		ActionForward forward=null;
		Action action=null;
		
		try {
			action= commandMap.get(command);
			
			   if (action == null) {
	                System.out.println("명령어 : "+command+"는 잘못된 명령입니다.");
	                return;
	            }
		
		
		forward=action.execute(request, response);
		
		if(forward!= null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getNextPath());
			}else {
				RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getNextPath());
				dispatcher.forward(request, response);
			}
		}
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doProcess(request,response);
		 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 doProcess(request,response);
		 }

	
	
}
