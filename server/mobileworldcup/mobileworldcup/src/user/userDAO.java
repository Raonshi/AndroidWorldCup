package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utill.DatabaseUtill;

public class userDAO {
	public int join(String user_id, String user_pwd ){ //DB member테이블에 입력
		String SQL = "INSERT INTO USER (user_id, user_pwd)" + " VALUES (?, ?)";
		
		try{
			Connection conn = DatabaseUtill.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1,user_id);
			 pstmt.setString(2,user_pwd);
			 
			 return pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}
	
	public ArrayList<userDTO> list() throws ClassNotFoundException, SQLException{ //조회
		
		Connection conn = DatabaseUtill.getConnection();
		String SQL = "SELECT * FROM user";
		ResultSet rs = null;
		Statement stmt = null;
		ArrayList<userDTO> list = new ArrayList<userDTO>();
		stmt = conn.createStatement();
		rs = stmt.executeQuery(SQL);
		
		while(rs.next())
		{
			userDTO user = new userDTO();		
			user.setUser_id(rs.getString("user_id"));
			user.setUser_pwd(rs.getString("user_pwd"));
			
			list.add(user);
		}
		if(rs != null) rs.close();
		if(stmt != null) stmt.close();
		if(conn != null) conn.close();
		
		return list;
	}
}
