package dogwhiz.feedback;

import dogwhiz.model.Board;

public class FeedbackBoard extends Board {
	private boolean secret;

	
    public FeedbackBoard(String title, String content, String writer, boolean secret) {
		super(title, content, writer);
		this.secret = secret;
        this.category = "피드백";
	}
	
	
	public boolean isSecret() {
		return secret;
	}

	public void setSecret(boolean secret) {
		this.secret = secret;
	}
}
