package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import com.test.dbconn.DBConnection;
import com.test.dto.joinDTO;

public class memberDAO {

	private static memberDAO instance;

	private memberDAO() {
	}

	public static memberDAO getInstance() {
		if (instance == null)
			instance = new memberDAO();
		return instance;
	}

	public void joinMember(joinDTO bean) throws SQLException {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO shop VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPasswd1());
			pstmt.setString(4, bean.getTel1());
			pstmt.setString(5, bean.getTel2());
			pstmt.setString(6, bean.getTel3());
			pstmt.setString(7, bean.getAddr());
			pstmt.setString(8, bean.getBirth());
			pstmt.setString(9, bean.getGender());

			pstmt.executeUpdate();

			conn.commit();

		} catch (ClassNotFoundException | NamingException | SQLException sqle) {
				conn.rollback();
	            throw new RuntimeException(sqle.getMessage());
		}finally{
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}

	public int loginCheck(String id, String passwd1) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbPW = ""; // db에서 꺼낸 비밀번호를 담을 변수
		int x = -1;

		try {
			// 쿼리 - 먼저 입력된 아이디로 DB에서 비밀번호를 조회한다.
			StringBuffer query = new StringBuffer();
			query.append("SELECT passwd1 FROM shop WHERE ID=?");

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) // 입려된 아이디에 해당하는 비번 있을경우
			{
				dbPW = rs.getString("password1"); // 비번을 변수에 넣는다.

				if (dbPW.equals(passwd1))
					x = 1; // 비밀번호 db와 비교해서 확인
				else
					x = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패

			} else {
				x = -1; // 해당 아이디가 없을 경우
			}

			return x;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end loginCheck()

	public void memberUpdate(joinDTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE shop SET id=?, name=?, passwd1=?, tel1=?, tel2=?, tel3=?, addr=?, birth=? where id=?");

			pstmt = conn.prepareStatement(sql.toString());

			conn.setAutoCommit(false);

			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPasswd1());
			pstmt.setString(4, bean.getTel1());
			pstmt.setString(5, bean.getTel2());
			pstmt.setString(6, bean.getTel3());
			pstmt.setString(7, bean.getAddr());
			pstmt.setString(8, bean.getBirth());
			pstmt.setString(9, bean.getGender());

			pstmt.executeUpdate();

			conn.commit();

		} catch (Exception sqle) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}

	}

	public joinDTO getUserInfo(String id) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		joinDTO bean = null;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM shop WHERE id=?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				bean.setId(rs.getString("id"));
				bean.setPasswd1(rs.getString("passwd1"));
				bean.setName(rs.getString("name"));
				bean.setTel1(rs.getString("tel1"));
				bean.setTel2(rs.getString("tel2"));
				bean.setTel3(rs.getString("tel3"));
				bean.setAddr(rs.getString("addr"));
				bean.setBirth(rs.getString("birth"));
				bean.setGender(rs.getString("gender"));
			}

		} catch (Exception sqle) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return bean;

	}

	@SuppressWarnings("resource")
	public int memberDelete(String id, String passwd1) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbPass = "";
		int x = -1;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql1 = new StringBuffer();
			sql1.append("SELECT passwd1 FROM shop WHERE id=?");

			StringBuffer sql2 = new StringBuffer();
			sql2.append("DELETE FROM shop WHERE id=?");

			conn.setAutoCommit(false);

			pstmt = conn.prepareStatement(sql1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				dbPass = rs.getString("passwd1");
				if (dbPass.equals(passwd1)) {
					pstmt = conn.prepareStatement(sql2.toString());
					pstmt.setString(1, id);
					pstmt.executeQuery();
					conn.commit();
					x = 1; // 삭제성공
				} else {
					x = 0; // 비밀번호 틀림
				}

			}

		} catch (Exception sqle) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
				if (rs != null) {
					rs.close();
				}

			} catch (Exception e) {
				// TODO: handle exception
			}

		}
		return x;
	}

}
