package dogwhiz.dictionary;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DictionaryService {
	@Autowired
	@Qualifier("DictionaryMySQL")
	private DictionaryRepository repo;
	
	@Transactional(readOnly = true)
	public List<DictionaryBoard> getAllDictionaryDesc() {
		return repo.getAllDictionaryDesc();
	}
	
	@Transactional(readOnly = true)
	public DictionaryBoard getDictionarywithNo(int no) {
		return repo.getDictionarywithNo(no);
	}
	
	@Transactional(readOnly = true)
	public List<DictionaryBoard> getDictionaryWithTitle(String title) {
		return repo.getDictionaryWithTitle(title);
	}
	
	@Transactional(readOnly = true)
	public List<DictionaryBoard> getDictionaryWithSubCategory(String subCategory) {
		return repo.getDictionaryWithSubCategory(subCategory);
	}
	
	@Transactional(readOnly = true)
	public List<DictionaryBoard> getDictionaryBetweenNoDesc(int start, int end) {
		return repo.getDictionaryBetweenNoDesc(start, end);
	}
	
	@Transactional(readOnly = true)
	public int getDictionaryCount() {
		return repo.getDictionaryCount();
	}
	
	@Transactional(readOnly = true)
	public int getNewDictionarytNo() {
		return repo.getNewDictionarytNo();
	}
	
	@Transactional
	public int insertDictionary(DictionaryBoard dictionaryBoard) {
		return repo.insertDictionary(dictionaryBoard);
	}
	
	@Transactional
	public int updateDictionarywithNo(int no, String subCategory, String title, String content) {
		return repo.updateDictionarywithNo(no, subCategory, title, content);
	}
	
	@Transactional
	public int deleteDictionarywithNo(int no) {
		return repo.deleteDictionarywithNo(no);
	}
	
	@Transactional
	public int addViewCount(int no) {
		return repo.addViewCount(no);
	}
	
	@Transactional
	public int addLikeCount(int no) {
		return repo.addLikeCount(no);
	}
}
