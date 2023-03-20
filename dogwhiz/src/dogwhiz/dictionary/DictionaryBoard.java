package dogwhiz.dictionary;

import dogwhiz.model.Board;

public class DictionaryBoard extends Board {
	private String subCategoryD;
	
    public DictionaryBoard() {
        this.category = "개과사전";
        this.writer = "관리자";
    }
	
	public DictionaryBoard(String subCategoryD, String title, String content) {
		super(title, content, "관리자");
		this.category = "개과사전";
		this.subCategoryD = subCategoryD;
	}

	public String getSubCategoryD() {
		return subCategoryD;
	}

	public void setSubCategoryD(String subCategoryD) {
		this.subCategoryD = subCategoryD;
	}
}
