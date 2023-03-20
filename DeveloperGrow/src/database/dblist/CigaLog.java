package database.dblist;

public class CigaLog {
	private int logId;
	private String message;
	private int userId;
	private int infoId;
	public CigaLog(int logId, String message, int userId, int infoId) {
		super();
		this.logId = logId;
		this.message = message;
		this.userId = userId;
		this.infoId = infoId;
	}
	public CigaLog(String message, int userId, int infoId) {
		super();
		this.message = message;
		this.userId = userId;
		this.infoId = infoId;
	}
	public int getLogId() {
		return logId;
	}
	public void setLogId(int logId) {
		this.logId = logId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	@Override
	public String toString() {
		return "CigaLog [logId=" + logId + ", message=" + message + ", userId=" + userId + ", infoId=" + infoId + "]";
	}
}
