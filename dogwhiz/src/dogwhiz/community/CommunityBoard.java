package dogwhiz.community;

import dogwhiz.model.Board;

public class CommunityBoard extends Board {
	private String subCategoryC;
	
	public CommunityBoard(String subCategory, String title, String content, String writer) {
		super(title, content, writer);
		this.category = "커뮤니티";
		this.subCategoryC = subCategory;
	}

	public CommunityBoard() {
		this.category = "커뮤니티";
	}

	public String getSubCategoryC() {
		return subCategoryC;
	}

	public void setSubCategoryC(String subCategoryC) {
		this.subCategoryC = subCategoryC;
	}
}
