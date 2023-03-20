package database.dblist;

public class User {
	private int id;
	private String userId;
	private String userPw;
	private String userNickname;
	
	public User(int id, String userId, String userPw, String userNickname) {
		super();
		this.id = id;
		this.userId = userId;
		this.userPw = userPw;
		this.userNickname = userNickname;
	}
	public User(String userId, String userPw, String userNickname) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userNickname = userNickname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userPw=" + userPw + ", userNickname=" + userNickname + "]";
	}
}
