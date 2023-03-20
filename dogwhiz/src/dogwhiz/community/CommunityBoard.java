package dogwhiz.community;

import dogwhiz.model.Board;

public class CommunityBoard extends Board {
	private String subCategory;
	
	public CommunityBoard(String subCategory, String title, String content, String writer) {
		super(title, content, writer);
		this.category = "커뮤니티";
		this.subCategory = subCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
}
