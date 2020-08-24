package com.test.board.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.action.BoardDeleteAction;
import com.test.board.action.BoardDetailAction;
import com.test.board.action.BoardFormChangeAction;
import com.test.board.action.BoardListAction;
import com.test.board.action.BoardUpdateAction;
import com.test.board.action.BoardUpdateFormAction;
import com.test.board.action.BoardWriteAction;
import com.test.member.action.Action;
import com.test.member.action.ActionForward;

@WebServlet("*.bo")
public class BoardController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private HashMap<String, Action> commandMap;
	
	 public void init(ServletConfig config) throws ServletException {    
	        loadProperties("com/test/board/properties/BoardCommand");
	    }
	    
	    /**
	     * 프로퍼티 파일에서 키값과 클래스 정보를 추출하여 그것을 Map에 저장한다.
	     * @param filePath 프로퍼티 파일의 경로
	     */
	    private void loadProperties(String filePath) 
	    {
	        commandMap = new HashMap<String, Action>();
	        
	        ResourceBundle rb = ResourceBundle.getBundle(filePath);
	        Enumeration<String> actionEnum = rb.getKeys(); // 키값을 가져온다.
	         
	        while (actionEnum.hasMoreElements()) 
	        {
	            // 명령어를 가져온다.
	            String command = actionEnum.nextElement(); 
	            // 명령어에 해당하는 Action 클래스 이름을 가져온다.
	            String className = rb.getString(command); 
	            
	            try {
	                 Class actionClass = Class.forName(className); // 클래스 생성
	                 Action actionInstance = (Action)actionClass.newInstance(); // 클래스의 객체를 생성
	                 
	                 // 화면전환 Action 인지 확인한다. 화면전환 Action이면 명령어를 전달한다.
	                 if(className.equals("com.test.board.action.BoardFormChangeAction")){
	                     BoardFormChangeAction bf = (BoardFormChangeAction)actionInstance;
	                     bf.setCommand(command);
	                 }
	                 
	                 // 맵에 명령어와 Action을 담는다.
	                 commandMap.put(command, actionInstance);
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	    /**
	     * GET 방식일 경우 doGet()
	     */
	    public void doGet(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	            doProcess(request,response);
	    }      
	        
	    /**
	     * POST 방식일 경우 doPost()
	     */
	    public void doPost(HttpServletRequest request, HttpServletResponse response) 
	            throws ServletException, IOException {
	            doProcess(request,response);
	    }
	 
	    /**
	     * 명령어에 따른 해당 Action을 지정해 준다.
	     * @param request
	     * @param response
	     * @throws ServletException
	     * @throws IOException
	     */
	    private void doProcess(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        
	        // 넘어온 커맨드를 추출하는 과정
	        String requestURI = request.getRequestURI();
	        int cmdIdx = requestURI.lastIndexOf("/") + 1;
	        String command = requestURI.substring(cmdIdx);
	 
	        // URI, command 확인
	        // System.out.println("requestURI : "+requestURI);
	        System.out.println("Board cmd : "+command);
	        
	        ActionForward forward = null;
	        Action action = null;
	        
	        try {
	            // 맵에서 명령어에 해당하는 Action을 가져온다.
	            action = commandMap.get(command);
	            
	            if (action == null) {
	                System.out.println("명령어 : "+command+"는 잘못된 명령입니다.");
	                return;
	            }
	 
	            forward = action.execute(request, response);
	            
	            /*
	             * 화면이동 - isRedirext() 값에 따라 sendRedirect 또는 forward를 사용
	             * sendRedirect : 새로운 페이지에서는 request와 response객체가 새롭게 생성된다.
	             * forward : 현재 실행중인 페이지와 forwad에 의해 호출될 페이지는 request와 response 객체를 공유
	             */

//	            if(command.equals("BoardWriteAction.bo")) // 게시판 글쓰기
//	            {
//	                forward=new ActionForward();
//	                forward.setRedirect(false);
//	                forward.setNextPath("../member/board/BoardWriteForm.jsp");
//	            }
//	            else if(command.equals("BoardListAction.bo"))    // 게시판 목록
//	            {
//	                forward=new ActionForward();
//	                forward.setRedirect(false);
//	                forward.setNextPath("board/BoardListForm.jsp");
//	            }
//	            else if(command.equals("BoardDetailAction.bo"))    // 게시글 보기
//	            {
//	                forward=new ActionForward();
//	                forward.setRedirect(false);
//	                forward.setNextPath("board/BoardDetailForm.jsp");
//	            }
//	            else if(command.equals("BoardDeleteAction.bo"))    // 게시글 삭제
//	            {
//	                forward=new ActionForward();
//	                forward.setRedirect(false);
//	                forward.setNextPath("BoardListAction.bo");
//	            }
//	            else if(command.equals("BoardReplyFormAction.bo"))    // 게시글 댓글
//	            {
//	                forward=new ActionForward();
//	                forward.setRedirect(false);
//	                forward.setNextPath("../member/board/BoardReplyForm.jsp");
//	            }
//	            else if(command.equals("BoardUpdateFormAction.bo"))    // 게시글 수정
//	            {
//	                forward=new ActionForward();
//	                forward.setRedirect(false);
//	                forward.setNextPath("../member/board/BoardUpdateForm.jsp");
//	            }
//	            else if(command.equals("BoardWriteAction.bo")) // 글쓰기 실행
//	            {
//	                action = new BoardWriteAction();
//	                forward = action.execute(request, response);
//	            }
//	            else if(command.equals("BoardListAction.bo")) // 게시글 실행
//	            {
//	                action = new BoardListAction();
//	                forward = action.execute(request, response);
//	            }
//	            else if(command.equals("BoardDetailAction.bo")) // 조회 실행
//	            {
//	                action = new BoardDetailAction();
//	                forward = action.execute(request, response);
//	            }
	            
//	            else if(command.equals("BoardUpdateFormAction.bo")) // 조회 실행
//	            {
//	                action = new BoardUpdateFormAction();
//	                forward = action.execute(request, response);
//	            }
//	            else if(command.equals("BoardUpdateAction.bo")) // 조회 실행
//	            {
//	                action = new BoardUpdateAction();
//	                forward = action.execute(request, response);
//	            }
	            
//	            else if(command.equals("BoardReplyAction.bo")) // 댓글 입력 실행
//	            {
//	                action = new BoardReplyAction();
//	                forward = action.execute(request, response);
//	            }
//	            else if(command.equals("BoardReplyFormAction.bo")) // 댓글 실행
//	            {
//	                action = new BoardReplyFormAction();
//	                forward = action.execute(request, response);
//	            }
//	            else if(command.equals("BoardDeleteAction.bo")) // 삭제 실행
//	            {
//	                action = new BoardDeleteAction();
//	                forward = action.execute(request, response);
//	            }
//	            
	            
	            if(forward != null){
                if (forward.isRedirect()) {
                    response.sendRedirect(forward.getNextPath());
                } else {
                    RequestDispatcher dispatcher = request
                            .getRequestDispatcher(forward.getNextPath());
                    dispatcher.forward(request, response);
                }
            }
	            
	 
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } // end doProcess        


}
