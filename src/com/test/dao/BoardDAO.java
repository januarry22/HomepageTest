package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.test.dbconn.DBConnection;
import com.test.dto.BoardBean;

public class BoardDAO {
	

	    private Connection conn;
	    private PreparedStatement pstmt;
	    private ResultSet rs;
	    
	    private static BoardDAO instance;
	    
	    private BoardDAO(){}
	    public static BoardDAO getInstance(){
	        if(instance==null)
	            instance=new BoardDAO();
	        return instance;
	    }
	    
	    // 시퀀스를 가져온다.
	    public int getSeq()
	    {
	        int result = 1;
	        
	        try {
	            conn = DBConnection.getConnection();
	            
	            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
	            StringBuffer sql = new StringBuffer();
	            sql.append("SELECT BOARD_NUM.NEXTVAL FROM DUAL");
	            
	            pstmt = conn.prepareStatement(sql.toString());
	            // 쿼리 실행
	            rs = pstmt.executeQuery();
	            
	            if(rs.next())   {
	            	result = rs.getInt(1);
	            }
	 
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	        
	        close();
	        return result;    
	    } // end getSeq
	    
	    // 글 삽입
	    public boolean boardInsert(BoardBean board)
	    {
	        boolean result = false;
	        
	        try {
	            conn = DBConnection.getConnection();
	            
	            StringBuffer sql = new StringBuffer();
	            sql.append("INSERT INTO BOARD (BOARD_NUM, BOARD_ID, BOARD_SUBJECT, BOARD_CONTENT, RE_REF, RE_LEV, RE_SEQ, hit, reg_date) VALUES(?,?,?,?,?,?,?,?,sysdate)");

	            // 시퀀스 값을 글번호와 그룹번호로 사용
	            int num = board.getBoard_num();
	 
	            pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setInt(1, num);
	            pstmt.setString(2, board.getBoard_id());
	            pstmt.setString(3, board.getBoard_subject());
	            pstmt.setString(4, board.getBoard_content());
	            pstmt.setInt(5, num);
	            pstmt.setInt(6, 0);
	            pstmt.setInt(7, 0);
	            pstmt.setInt(8, 0);
	            
	            int flag = pstmt.executeUpdate();
	            if(flag > 0){
	               result = true;
	               conn.commit(); 
	            }

	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	        close();
	        return result;    
	    } // end boardInsert();
	    
	    // DB 자원해제
	    private void close()
	    {
	        try {
	            if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	            if ( conn != null ){ conn.close(); conn=null;    }
	        } catch (Exception e) {
	            throw new RuntimeException(e.getMessage());
	        }
	    } // end close()
	}



