package dogwhiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dogwhiz.model.Post;
import dogwhiz.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	@Qualifier("PostMySQL")
	private PostRepository repo;
	
	@Transactional(readOnly = true)
	public List<Post> getAllPost() {
		return repo.getAllPost();
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostByCategory(String category) {
		return repo.getPostByCategory(category);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostBySubCategory(String category, String subCategory) {
		return repo.getPostBySubCategory(category, subCategory);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostByTitle(String title) {
		return repo.getPostByTitle(title);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostByWriter(String writer) {
		return repo.getPostByWriter(writer);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostBetweenNo(int start, int end) {
		return repo.getPostBetweenNo(start, end);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostByCategoryBetweenNo(String category, int start, int end) {
		return repo.getPostByCategoryBetweenNo(category, start, end);
	}
	
	@Transactional(readOnly = true)
	public List<Post> getPostBySubCategoryBetweenNo(String category, String subCategory, int start, int end) {
		return repo.getPostBySubCategoryBetweenNo(category, subCategory, start, end);
	}
	
	@Transactional(readOnly = true)
	public Post getPostByNo(int no) {
		return repo.getPostByNo(no);
	}
	
	@Transactional(readOnly = true)
	public int getAllPostCount() {
		return repo.getAllPostCount();
	}
	
	@Transactional(readOnly = true)
	public int getPostCountByCategory(String category) {
		return repo.getPostCountByCategory(category);
	}
	
	@Transactional(readOnly = true)
	public int getPostCountBySubCategory(String category, String subCategory) {
		return repo.getPostCountBySubCategory(category, subCategory);
	}
	
	@Transactional(readOnly = true)
	public int getPostCountByTitle(String title) {
		return repo.getPostCountByTitle(title);
	}
	
	@Transactional(readOnly = true)
	public int getPostCountByWriter(String writer) {
		return repo.getPostCountByWriter(writer);
	}
	
	@Transactional
	public int insertPost(Post post) {
		return repo.insertPost(post);
	}
	
	@Transactional
	public int updatePost(Post post) {
		return repo.updatePost(post);
	}
	
	@Transactional
	public int deletePost(int no) {
		return repo.deletePost(no);
	}
	
	@Transactional
	public int increaseViewCount(int no) {
		return repo.increaseViewCount(no);
	}
}
