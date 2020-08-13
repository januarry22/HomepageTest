package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.test.controller.DBconnect;

public class loginDAO {


	public String loginCheck(String id, String passwd1) throws SQLException, Exception {
		
		
		String name=null;
		Connection conn=null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		
		try{
			
			conn=DBconnect.getConnection();
			String sql="SELECT name FROM shop where id=? and passwd1=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, passwd1);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				name= rs.getString("name");
			
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt!=null){
				pstmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			if(rs!=null){
				rs.close();
			}
		}
		
		
		
		return name;
	}

	
}
