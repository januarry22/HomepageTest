package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.test.dbconn.DBConnection;
import com.test.dto.BoardBean;

public class BoardDAO {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static BoardDAO instance;

	private BoardDAO() {
	}

	public static BoardDAO getInstance() {
		if (instance == null)
			instance = new BoardDAO();
		return instance;
	}

	public boolean boardInsert(BoardBean board) {
		boolean result = false;

		try {
			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(
					"INSERT INTO BOARD (board_id, board_subject, board_content, board_file, hit) "
							+ "VALUES(?,?,?,?,?)");

			int num = board.getBoard_num();
			int ref=board.getRe_ref();
			int parent= board.getBoard_parent();

//			if(parent==0) ref=num; // 부모글일 경우 그룹번호와 동일
			
			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, num);
			pstmt.setString(1, board.getBoard_id());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());
//			pstmt.setInt(6, ref);
			pstmt.setInt(5, board.getHit());
//			pstmt.setInt(8, parent);
//			pstmt.setInt(5, board.getHit());

//			if (board.getRe_seq()==0) {			//re_seq==0 은 답변글이 없는경우, 즉 부모글
//				pstmt.setInt(5, num);
//			}else {
//				pstmt.setInt(5, board.getRe_ref());
//			}
				
//			pstmt.setInt(6, board.getRe_lev());
//			pstmt.setInt(7, board.getRe_seq());

			int flag = pstmt.executeUpdate();

			if (flag > 0) {
				result = true;
				conn.commit();
			}

		} catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } 

			throw new RuntimeException(e.getMessage());
		}
		close();
		return result;
	} // end boardInsert();

//	public int getMaxNum() {
//
//		int maxNum = 0;
//
//		try {
//			conn = DBConnection.getConnection();
//
//			StringBuffer sql = new StringBuffer();
//
//			sql.append("SELECT nvl(max(board_num),0) from board");
//
//			pstmt = conn.prepareStatement(sql.toString());
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//				maxNum = rs.getInt(1);
//			}
//
//			close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//
//		return maxNum;
//	}

	public ArrayList<BoardBean> getBoardList(HashMap<String, Object> listOpt) {

		ArrayList<BoardBean> list = new ArrayList<BoardBean>();

		String opt = (String) listOpt.get("opt");
		String condition = (String) listOpt.get("condition");
		int start = (Integer) listOpt.get("start");

		try {

			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();

			if (opt == null) {

//				sql.append("select * from ");
//				sql.append("(select rownum rnum, board_num, board_id, board_subject");
//				sql.append(", board_content, board_file, hit, re_ref");
//				sql.append(",re_lev, re_seq");
//				sql.append("FROM");
//				sql.append(" (select * from board order by RE_REF desc, RE_SEQ asc)) ");
//				sql.append("where rnum>=? and rnum<=?");
				sql.append("SELECT * from(SELECT ROW_number() over() AS rnum ,board_num, board_id, board_subject, board_content, board_file, hit FROM board) AS tb WHERE tb.rnum>=?  AND tb.rnum <=? order by board_num");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start +2);

				// StringBuffer를 비운다.
				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) // 제목으로 검색
			{
//				sql.append("select * from ");
//				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
//				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
//				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
//				sql.append("FROM ");
//				sql.append("(select * from BOARD where BOARD_SUBJECT like ? ");
//				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
				sql.append("select * from board where board_subject like ?");
				sql.append("ORDER BY board_num LIMIT ? ,?");
				
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) // 내용으로 검색
			{
//				sql.append("select * from ");
//				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
//				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
//				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
//				sql.append("FROM ");
//				sql.append("(select * from BOARD where BOARD_CONTENT like ? ");
//				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
//				sql.append("where rnum>=? and rnum<=?");
				sql.append("select * from board where board_content like ?");
				sql.append("ORDER BY board_num LIMIT ? ,?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) // 제목+내용으로 검색
			{
//				sql.append("select * from ");
//				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
//				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
//				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
//				sql.append("FROM ");
//				sql.append("(select * from BOARD where BOARD_SUBJECT like ? OR BOARD_CONTENT like ? ");
//				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
//				sql.append("where rnum>=? and rnum<=?");
				sql.append("select * from board where board_subject like ? or board_content like ?");
				sql.append("ORDER BY board_num LIMIT ?, ?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setString(2, "%" + condition + "%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) // 글쓴이로 검색
			{
//				sql.append("select * from ");
//				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
//				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
//				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
//				sql.append("FROM ");
//				sql.append("(select * from BOARD where BOARD_ID like ? ");
//				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
//				sql.append("where rnum>=? and rnum<=?");
//				sql.append("select * from board where board_id like ?");
				sql.append("SELECT * FROM board WHERE board_id LIKE ? ORDER BY board_num LIMIT ?,?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

				BoardBean bean = new BoardBean();
				bean.setBoard_num(rs.getInt("board_num"));
				bean.setBoard_id(rs.getString("board_id"));
				bean.setBoard_subject(rs.getString("board_subject"));
				bean.setBoard_content(rs.getString("board_content"));
				bean.setBoard_file(rs.getString("board_file"));
				bean.setHit(rs.getInt("hit"));
//				bean.setRe_lev(rs.getInt("re_lev"));
//				bean.setRe_ref(rs.getInt("re_ref"));
//				bean.setRe_seq(rs.getInt("re_seq"));

				list.add(bean);
			
			}
		

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			close();
		}

		return list;

	}// end getBoardList

	public int getBoardListCount(HashMap<String, Object> listOpt) {

		int result = 0;

		String opt = (String) listOpt.get("opt");
		String condition = (String) listOpt.get("condition");

		try {

			conn = DBConnection.getConnection();
			StringBuffer sql = new StringBuffer();

			if (opt == null) {
				sql.append("select count(*) from board");
				pstmt = conn.prepareStatement(sql.toString());

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) // 제목으로 검색한 글의 개수
			{
				sql.append("select count(*) from board where BOARD_SUBJECT like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) // 내용으로 검색한 글의 개수
			{
				sql.append("select count(*) from board where BOARD_CONTENT like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) // 제목+내용으로 검색한 글의 개수
			{
				sql.append("select count(*) from board ");
				sql.append("where BOARD_SUBJECT like ? or BOARD_CONTENT like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');
				pstmt.setString(2, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) // 글쓴이로 검색한 글의 개수
			{
				sql.append("select count(*) from board where BOARD_ID like ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, '%' + condition + '%');

				sql.delete(0, sql.toString().length());
			}

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt(1);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		close();
		return result;
	}

//	public boolean nextPage(int pageNum) {
//		String sql = "SELECT * FROM board WHERE board_num<? board_num=1 oreder by board_num desc limit 10";
//
//		ArrayList<BoardBean> list = new ArrayList<BoardBean>();
//		try {
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, getNext() - (pageNum - 1) * 10);
//			rs = pstmt.executeQuery();
//
//			if (rs.next()) {
//
//				return true;
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return false;
//	}
//
//	private int getNext() {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	public BoardBean getDetail(int boardNum) {

		BoardBean bean=null;
		
		try {
			conn=DBConnection.getConnection();
			
			StringBuffer sql = new StringBuffer();
				sql.append("SELECT * FROM board WHERE board_num=?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				bean = new BoardBean();
				bean.setBoard_num(boardNum);
				bean.setBoard_id(rs.getString("board_id"));
				bean.setBoard_subject(rs.getString("board_subject"));
				bean.setBoard_content(rs.getString("board_content"));
//				bean.setBoard_file(rs.getString());
				bean.setHit(rs.getInt("hit"));
//				bean.setRe_ref(rs.getInt("RE_REF"));
//				bean.setRe_lev(rs.getInt("RE_LEV"));
//				bean.setRe_seq(rs.getInt("RE_SEQ"));
//				bean.setBoard_date(rs.getDate("BOARD_DATE"));

				

			}
		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		close();
		return bean;
	}
	
	public boolean updateCount(int boardNum)
    {
        boolean result = false;
        
        try {
            conn = DBConnection.getConnection();
            
            StringBuffer sql = new StringBuffer();
            sql.append("update board set hit = hit+1 where board_num = ?");
          
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.setInt(1, boardNum);
            
            int flag = pstmt.executeUpdate();
            if(flag > 0){
                result = true;
                conn.commit(); // 완료시 커밋
            }    
        } catch (Exception e) {
            try {
                conn.rollback(); // 오류시 롤백
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        }
        
        close();
        return result;
    } // end updateCount
	
	
//	public boolean updateReSeq(BoardBean bean) {
//		boolean result=false;
//		
//		int ref=bean.getRe_ref();
//		int seq= bean.getRe_seq();
//		
//		try {
//			StringBuffer sql= new StringBuffer();
//			
//			conn=DBConnection.getConnection();
//			conn.setAutoCommit(false);
//			
//			sql.append("UPDATE board SET re_seq = re_seq + 1 WHERE re_ref=? and re_seq > ?");
//			
//			pstmt=conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, ref);
//			pstmt.setInt(2, seq);
//			
//			int flag = pstmt.executeUpdate();
//			if(flag>0) {
//				result=true;
//				conn.commit();
//			}
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			 try {
//	                conn.rollback(); // 오류시 롤백
//	            } catch (SQLException sqle) {
//	                sqle.printStackTrace();
//	            }
//	            throw new RuntimeException(e.getMessage());
//		}
//		
//		close();
//		return result;
//		
//	}
	



	public boolean deleteBoard(int boardNum) {
		// TODO Auto-generated method stub
		boolean result=false;
		
		try {
			
			conn=DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql=new StringBuffer();
			sql.append("DELETE FROM board WHERE board_num=?");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setInt(1, boardNum);
			
			int flag=pstmt.executeUpdate();
			if(flag>0) {
				result=true;
				conn.commit();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		close();
		return result;
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

	public boolean updateBoard(BoardBean border) {
		// TODO Auto-generated method stub
		
		boolean result=false;
		
		try{
			conn=DBConnection.getConnection();
			conn.setAutoCommit(false);
			
			StringBuffer sql= new StringBuffer();
			
			sql.append("UPDATE board SET board_subject=?, board_content=? WHERE board_num=?");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, border.getBoard_subject());
			pstmt.setString(2, border.getBoard_content());
	//		pstmt.setString(3, border.getBoard_file());		//sql에도 빠짐
			pstmt.setInt(3, border.getBoard_num());
			
			int flag=pstmt.executeUpdate();
			
			if(flag>0) {
				result=true;
				conn.commit();
			}

			
		}catch (Exception e) {
			// TODO: handle exception
			 try {
	                conn.rollback(); // 오류시 롤백
	            } catch (SQLException sqle) {
	                sqle.printStackTrace();
	            }
	            throw new RuntimeException(e.getMessage());

		}
		
		close();
		return result;
	}
}
