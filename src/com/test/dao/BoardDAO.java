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
					"INSERT INTO BOARD (board_id, board_subject, board_content, board_file, hit, re_ref, re_lev, re_seq) "
							+ "VALUES(?,?,?,?,?,?,?,?)");

			int num = board.getBoard_num();

			pstmt = conn.prepareStatement(sql.toString());
//			pstmt.setInt(1, num);
			pstmt.setString(1, board.getBoard_id());
			pstmt.setString(2, board.getBoard_subject());
			pstmt.setString(3, board.getBoard_content());
			pstmt.setString(4, board.getBoard_file());

			pstmt.setInt(5, 0);
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			pstmt.setInt(8, 0);

			int flag = pstmt.executeUpdate();

			if (flag > 0) {
				result = true;
				conn.commit();
			}

		} catch (Exception e) {
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
				sql.append("SELECT * FROM board WHERE board_num between ? and ? ORDER BY board_num DESC");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setInt(1, start);
				pstmt.setInt(2, start +9);

				// StringBuffer를 비운다.
				sql.delete(0, sql.toString().length());
			} else if (opt.equals("0")) // 제목으로 검색
			{
				sql.append("select * from ");
				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
				sql.append("FROM ");
				sql.append("(select * from BOARD where BOARD_SUBJECT like ? ");
				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
				sql.append("where rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("1")) // 내용으로 검색
			{
				sql.append("select * from ");
				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
				sql.append("FROM ");
				sql.append("(select * from BOARD where BOARD_CONTENT like ? ");
				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
				sql.append("where rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("2")) // 제목+내용으로 검색
			{
				sql.append("select * from ");
				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
				sql.append("FROM ");
				sql.append("(select * from BOARD where BOARD_SUBJECT like ? OR BOARD_CONTENT like ? ");
				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
				sql.append("where rnum>=? and rnum<=?");

				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, "%" + condition + "%");
				pstmt.setString(2, "%" + condition + "%");
				pstmt.setInt(3, start);
				pstmt.setInt(4, start + 9);

				sql.delete(0, sql.toString().length());
			} else if (opt.equals("3")) // 글쓴이로 검색
			{
				sql.append("select * from ");
				sql.append("(select rownum rnum, BOARD_NUM, BOARD_ID, BOARD_SUBJECT");
				sql.append(", BOARD_CONTENT, BOARD_FILE, hit");
				sql.append(", RE_REF, RE_LEV, RE_SEQ ");
				sql.append("FROM ");
				sql.append("(select * from BOARD where BOARD_ID like ? ");
				sql.append("order BY RE_REF desc, RE_SEQ asc)) ");
				sql.append("where rnum>=? and rnum<=?");

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
				bean.setRe_lev(rs.getInt("re_lev"));
				bean.setRe_ref(rs.getInt("re_ref"));
				bean.setRe_seq(rs.getInt("re_seq"));

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

		String sql = "SELECT * FROM board WHERE board_num=?";

		try {

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				BoardBean bean = new BoardBean();
				bean.setBoard_num(boardNum);
				bean.setBoard_id(rs.getString("board_id"));
				bean.setBoard_subject(rs.getString("board_subject"));
				bean.setBoard_content(rs.getString("board_content"));
//				bean.setBoard_file(rs.getString());

				return bean;
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
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
