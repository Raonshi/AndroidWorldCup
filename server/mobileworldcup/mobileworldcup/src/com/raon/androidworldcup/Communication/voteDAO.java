package com.raon.androidworldcup.Communication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utill.DatabaseUtill;

public class voteDAO {
	public int join(String vote_title,  String user_id, int vote_item1, int vote_item2, int vote_item3, String vote_day){ //DB member테이블에 입력
		String SQL = "INSERT INTO VOTE (vote_title,  user_id, vote_item1, vote_item2, vote_item3, vote_day)" + " VALUES (?, ?, ?, ?, ?, ?)";
		
		try{
			Connection conn = DatabaseUtill.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			 pstmt.setString(1,vote_title);
			 //pstmt.setInt(2, vote_search);
			 pstmt.setString(2,user_id);
			 pstmt.setInt(3, vote_item1);
			 pstmt.setInt(4, vote_item2);
			 pstmt.setInt(5, vote_item3);
			 pstmt.setString(6,vote_day);
			 
			 return pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	public int modify(String vote_title, int vote_item1, int vote_item2, int vote_item3){
		String SQL = "UPDATE vote " +
				"SET vote_item1=?, vote_item2=?, vote_item3=? " +
				"WHERE vote_title=?";

		try{
			Connection conn = DatabaseUtill.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, vote_item1);
			pstmt.setInt(2, vote_item2);
			pstmt.setInt(3, vote_item3);
			pstmt.setString(4,vote_title);

			return pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		}
		return -1;
	}

	public ArrayList<voteDTO> list() throws ClassNotFoundException, SQLException{ //조회

			Connection conn = DatabaseUtill.getConnection();
			String SQL = "SELECT * FROM vote";
			ResultSet rs = null;
			Statement stmt = null;
			ArrayList<voteDTO> list = new ArrayList<voteDTO>();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(SQL);

			while(rs.next())
			{
				voteDTO vote = new voteDTO();
				vote.setVote_title(rs.getNString("vote_title"));
				//vote.setVote_item1(rs.getInt("vote_search"));
				vote.setVote_search(rs.getInt("vote_search"));
				vote.setUser_id(rs.getNString("user_id"));
				vote.setVote_item1(rs.getInt("vote_item1"));
				vote.setVote_item2(rs.getInt("vote_item2"));
				vote.setVote_item3(rs.getInt("vote_item3"));
				vote.setVote_day(rs.getNString("vote_day"));

				list.add(vote);
			}
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();

			return list;
		}
}
