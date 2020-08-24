package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.dbconn.DBConnection;
import com.test.dto.BoardBean;
import com.test.dto.CommentBean;

public class CommentDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static CommentDAO instance;

	private CommentDAO() {

	}

	public static CommentDAO getInstance() {
		if (instance == null)
			instance = new CommentDAO();
		return instance;
	}

//    public int getSeq() 
//    {
//        int result = 1;
//        try {
//            conn = DBConnection.getConnection();
//            
//            // 시퀀스 값을 가져온다. (DUAL : 시퀀스 값을 가져오기위한 임시 테이블)
//            StringBuffer sql = new StringBuffer();
//            sql.append("SELECT COMMENT_SEQ.NEXTVAL FROM DUAL");
// 
//            pstmt = conn.prepareStatement(sql.toString());
//            rs = pstmt.executeQuery(); // 쿼리 실행
// 
//            if (rs.next())    result = rs.getInt(1);
// 
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
// 
//        close();
//        return result;
//    } // end getSeq

	public boolean insertComment(CommentBean comment) {
		boolean result = false;

		try {

			conn=DBConnection.getConnection();
			
			conn.setAutoCommit(false);
			
			StringBuffer sql= new StringBuffer();
			sql.append("INSERT INTO board_comment(comment_num, comment_board, comment_id,"
					+ " comment_date, comment_parent, comment_content) values(?,?,?,sysdate(),?,?)");
			
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setInt(1, comment.getComment_num());
			pstmt.setInt(2, comment.getComment_board());
			pstmt.setString(3, comment.getComment_id());
//			pstmt.setInt(4, comment.getComment_parent());
			pstmt.setInt(4, 0);
			pstmt.setString(5, comment.getComment_content());
			
			int flag=pstmt.executeUpdate();
			
			if(flag>0) {
				result=true;
				conn.commit();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			try {
				conn.rollback();

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		close();
		return result;
	}//insertComment 
	
	public ArrayList<CommentBean> getCommentList(int boardNum){
		
		ArrayList<CommentBean> list=new ArrayList<CommentBean>();
		
		try {
			conn= DBConnection.getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select comment_num, comment_board, comment_id, comment_content"
					+ "from board_comment where comment_board =? ");
			
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				CommentBean comment= new CommentBean();
		//		comment.setComment_level(rs.getInt("level"));
				comment.setComment_num(rs.getInt("comment_num"));
				comment.setComment_board(rs.getInt("comment_board"));
				comment.setComment_id(rs.getString("comment_id"));
		//		comment.setComment_date(rs.getDate("comment_date"));
		//		comment.setComment_parent(rs.getInt("comment_parent"));
				comment.setComment_content(rs.getString("comment_content"));
				list.add(comment);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());

		}
		
		
		close();
		return list;
	}
	

	// DB 자원해제
	private void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	} // end close()

}
