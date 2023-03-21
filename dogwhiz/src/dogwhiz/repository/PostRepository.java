package dogwhiz.repository;

import java.util.List;

import dogwhiz.model.Post;

public interface PostRepository {
	public List<Post> getAllPost(); // 전체 게시글 목록 불러오기(내림차순)
	public List<Post> getBestPost(String category, int limit); // 커뮤니티에서 인기글 불러오기
	public List<Post> getPostByCategory(String category); // 카테고리 검색
	public List<Post> getPostByCategoryWithLimit(String category, int limit); // 카테고리 검색, 가져올 글 갯수
	public List<Post> getPostBySubCategory(String category, String subCategory); // 상세 카테고리 검색
	public List<Post> getPostByTitle(String title); // 제목 검색
	public List<Post> getPostByWriter(String writer); // 작성자 검색
	public List<Post> getPostBetweenNo(int start, int end); // 번호 사이의 게시글 검색
	public List<Post> getPostByCategoryBetweenNo(String category, int start, int end);
	public List<Post> getPostBySubCategoryBetweenNo(String category, String subCategory, int start, int end);
	
 	public Post getPostByNo(int no); // 게시글 번호 검색
 	
	public int getAllPostCount(); //전체 게시글 개수 세기
	public int getPostCountByCategory(String category); // 카테고리 게시글 개수 세기
	public int getPostCountBySubCategory(String category, String subCategory);
	public int getPostCountByTitle(String title);
	public int getPostCountByWriter(String writer);
	
	public int insertPost(Post post);
	public int updatePost(Post post);
	public int deletePost(int no);
	
	public int increaseViewCount(int no);
}
