package dogwhiz.announcement;

import java.util.List;

import dogwhiz.model.Board;

public interface AnnouncementRepository {
	
	public List<AnnouncementBoard> getAllAnnouncementDesc(); // 전체 게시글 내림차순 가져오기
	public List<AnnouncementBoard> getMainAnnouncementDesc(); // 전체 게시글 내림차순 가져오기 3개만
	
	public AnnouncementBoard getAnnouncementwithNo(int no); // no로 검색하여 가져오기
	public List<AnnouncementBoard> getAnnouncementWithTitle(String title); // title로 검색하여 가져오기
	public List<AnnouncementBoard> getAnnouncementIsImportant(Boolean important); // 필독 공지만 가져오기
	
	public List<AnnouncementBoard> getAnnouncementBetweenNoDesc(int start, int end); // 두 숫자 사이의 게시글 내림차순 가져오기
	
	public int getAnnouncementCount(); // 게시글 개수 세기
	public int getNewAnnouncementNo(); // 마지막 게시글의 No 가져오기
	
	public int insertAnnouncement(AnnouncementBoard announcementBoard); // 게시글 등록
	public int updateAnnouncementwithNo(int no, String title, String content); // 게시글 수정
	public int deleteAnnouncementwithNo(int no); // 게시글 삭제
	
	public int addViewCount(int no); // 조회수 증가
	public int addLikeCount(int no); // 추천수 증가
}
