package vote;

import java.io.Serializable;

public class voteDTO implements Serializable  {
	private static final long serialVersionUID = 1L;
	String vote_title;
	String user_id;
	int vote_item1;
	int vote_item2;
	int vote_item3;
	String vote_day;
	public String getVote_title() {
		return vote_title;
	}
	public void setVote_title(String vote_title) {
		this.vote_title = vote_title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getVote_item1() {
		return vote_item1;
	}
	public void setVote_item1(int i) {
		this.vote_item1 = i;
	}
	public int getVote_item2() {
		return vote_item2;
	}
	public void setVote_item2(int vote_item2) {
		this.vote_item2 = vote_item2;
	}
	public int getVote_item3() {
		return vote_item3;
	}
	public void setVote_item3(int vote_item3) {
		this.vote_item3 = vote_item3;
	}
	public String getVote_day() {
		return vote_day;
	}
	public void setVote_day(String vote_day) {
		this.vote_day = vote_day;
	}
	
}

