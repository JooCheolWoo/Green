package dogwhiz.community;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunityService {
	@Autowired
	@Qualifier("CommunityMySQL")
	private CommunityRepository repo;
	
	@Transactional(readOnly = true)
	public List<CommunityBoard> getAllcommunityDesc() {
		return repo.getAllcommunityDesc();
	}
	
	@Transactional(readOnly = true)
	public CommunityBoard getCommunitywithNo(int no) {
		return repo.getCommunitywithNo(no);
	}
	
	@Transactional(readOnly = true)
	public List<CommunityBoard> getCommunityWithTitle(String title) {
		return repo.getCommunityWithTitle(title);
	}
	
	@Transactional(readOnly = true)
	public List<CommunityBoard> getCommunityWithSubCategory(String subCategory) {
		return repo.getCommunityWithSubCategory(subCategory);
	}
	
	@Transactional(readOnly = true)
	public List<CommunityBoard> getCommunityBetweenNoDesc(int start, int end) {
		return repo.getCommunityBetweenNoDesc(start, end);
	}
	
	@Transactional(readOnly = true)
	public int getCommunityCount() {
		return repo.getCommunityCount();
	}
	
	@Transactional(readOnly = true)
	public int getNewCommunitytNo() {
		return repo.getNewCommunitytNo();
	}
	
	@Transactional
	public int insertCommunity(CommunityBoard communityBoard) {
		return repo.insertCommunity(communityBoard);
	}
	
	@Transactional
	public int updateCommunitywithNo(int no, String subCategory, String title, String content) {
		return repo.updateCommunitywithNo(no, subCategory, title, content);
	}
	
	@Transactional
	public int deleteCommunitywithNo(int no) {
		return repo.deleteCommunitywithNo(no);
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
