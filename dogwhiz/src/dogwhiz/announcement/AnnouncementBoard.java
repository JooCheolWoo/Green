package dogwhiz.announcement;

import dogwhiz.model.Board;

public class AnnouncementBoard extends Board {
    private boolean important;
    
    public AnnouncementBoard() {
        this.category = "공지";
        this.writer = "관리자";
    }
    
    public AnnouncementBoard(String title, String content, boolean important) {
		super(title, content, "관리자");
		this.important = important;
        this.category = "공지";
	}


	public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}