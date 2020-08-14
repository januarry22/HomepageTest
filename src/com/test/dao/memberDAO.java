package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.test.controller.DBconnect;
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

	public void joinMember(joinDTO bean) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO shop VALUES(?,?,?,?,?,?,?,?,?)");

			pstmt=conn.prepareStatement(sql.toString());
			
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

		} catch (Exception sqle){
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
	
	public void memberUpdate(joinDTO bean) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE shop SET id=?, name=?, passwd1=?, tel1=?, tel2=?, tel3=?, addr=?, birth=? where id=?");

			pstmt=conn.prepareStatement(sql.toString());
			
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

		} catch (Exception sqle){
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
		joinDTO bean=null;

		try {

			conn = DBConnection.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("SELECT * FROM shop WHERE id=?");

			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				
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


		} catch (Exception sqle){
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

}
