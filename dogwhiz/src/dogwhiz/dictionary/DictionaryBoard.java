package dogwhiz.dictionary;

import dogwhiz.model.Board;

public class DictionaryBoard extends Board {
	private String subCategory;
	
    public DictionaryBoard() {
        this.category = "개과사전";
        this.writer = "관리자";
    }
	
	public DictionaryBoard(String subCategory, String title, String content) {
		super(title, content, "관리자");
		this.category = "개과사전";
		this.subCategory = subCategory;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
}
