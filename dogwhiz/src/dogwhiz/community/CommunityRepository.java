package dogwhiz.community;

import java.util.List;

import dogwhiz.model.Board;

public interface CommunityRepository {
	
	public List<CommunityBoard> getAllcommunityDesc(); // 전체 게시글 내림차순 가져오기
	
	public CommunityBoard getCommunitywithNo(int no); // no로 검색하여 가져오기
	public List<CommunityBoard> getCommunityWithTitle(String title); // title로 검색하여 가져오기
	public List<CommunityBoard> getCommunityWithSubCategory(String subCategory); // 서브카테고리 검색하여 가져오기
	
	public List<CommunityBoard> getCommunityBetweenNoDesc(int start, int end); // 두 숫자 사이의 게시글 내림차순 가져오기
	
	public int getCommunityCount(); // 게시글 개수 세기
	public int getNewCommunitytNo(); // 마지막 게시글의 No 가져오기
	
	public int insertCommunity(CommunityBoard communityBoard); // 게시글 등록
	public int updateCommunitywithNo(int no, String subCategory, String title, String content); // 게시글 수정
	public int deleteCommunitywithNo(int no); // 게시글 삭제
	
	public int addViewCount(int no); // 조회수 증가
	public int addLikeCount(int no); // 추천수 증가
}
