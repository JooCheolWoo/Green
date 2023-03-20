package dogwhiz.announcement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnnouncementService {
	@Autowired
	@Qualifier("AnnoucementMySQL")
	private AnnouncementRepository repo;
	
	@Transactional(readOnly = true)
	public List<AnnouncementBoard> getAllAnnouncementDesc() {
		return repo.getAllAnnouncementDesc();
	}
	
	@Transactional(readOnly = true)
	public AnnouncementBoard getAnnouncementwithNo(int no) {
		return repo.getAnnouncementwithNo(no);
	}
	
	@Transactional(readOnly = true)
	public List<AnnouncementBoard> getAnnouncementWithTitle(String title) {
		return repo.getAnnouncementWithTitle(title);
	}
	
	@Transactional(readOnly = true)
	public List<AnnouncementBoard> getAnnouncementIsImportant(Boolean important) {
		return repo.getAnnouncementIsImportant(important);
	}
	
	@Transactional(readOnly = true)
	public List<AnnouncementBoard> getAnnouncementBetweenNoDesc(int start, int end) {
		return repo.getAnnouncementBetweenNoDesc(start, end);
	}
	
	@Transactional(readOnly = true)
	public int getAnnouncementCount() {
		return repo.getAnnouncementCount();
	}
	
	@Transactional(readOnly = true)
	public int getNewAnnouncementNo() {
		return repo.getNewAnnouncementNo();
	}
	
	@Transactional
	public int insertAnnouncement(AnnouncementBoard announcementBoard) {
		return repo.insertAnnouncement(announcementBoard);
	}
	
	@Transactional
	public int updateAnnouncementwithNo(int no, String title, String content) {
		return repo.updateAnnouncementwithNo(no, title, content);
	}
	
	@Transactional
	public int deleteAnnouncementwithNo(int no) {
		return repo.deleteAnnouncementwithNo(no);
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
