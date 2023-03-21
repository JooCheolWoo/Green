package dogwhiz.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import dogwhiz.model.Post;

@Repository("PostMySQL")
public class PostRepositoryImpl implements PostRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private RowMapper<Post> rowMapper = new RowMapper<Post>() {
		@Override
		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			int no = rs.getInt("no");
			String category = rs.getString("category");
			String subCategory = rs.getString("sub_category");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
			LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
			int viewCount = rs.getInt("view_count");
			Boolean important = rs.getBoolean("important");
			
			Post post = new Post();
			post.setNo(no);
			post.setCategory(category);
			post.setSubCategory(subCategory);
			post.setTitle(title);
			post.setContent(content);
			post.setWriter(writer);
			post.setCreatedAt(createdAt);
			post.setUpdatedAt(updatedAt);
			post.setViewCount(viewCount);
			post.setImportant(important);
			return post;
		}
	};	

	@Override
	public List<Post> getAllPost() {
		String query = "SELECT * FROM post ORDER BY created_at DESC";
		return jdbcTemplate.query(query, rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostByCategory(String category) {
		String query = "SELECT * FROM post WHERE category = ? ORDER BY created_at DESC";
		return jdbcTemplate.query(query, new Object[]{category} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostBySubCategory(String category, String subCategory) {
		String query = "SELECT * FROM post WHERE category = ? AND sub_category = ? ORDER BY created_at DESC";
		return jdbcTemplate.query(query, new Object[]{category, subCategory} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostByTitle(String title) {
		String query = "SELECT * FROM post WHERE title LIKE %?% ORDER BY created_at DESC";
		return jdbcTemplate.query(query, new Object[]{title} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostByWriter(String writer) {
		String query = "SELECT * FROM post WHERE writer LIKE %?% ORDER BY created_at DESC";
		return jdbcTemplate.query(query, new Object[]{writer} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostBetweenNo(int start, int end) {
		String query = "SELECT * FROM post ORDER BY created_at DESC LIMIT ?, ?";
		return jdbcTemplate.query(query, new Object[]{start, end} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Post getPostByNo(int no) {
		String query = "SELECT * FROM post WHERE no = ?";
		List<Post> list = jdbcTemplate.query(query, new Object[]{no} , rowMapper);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public int getAllPostCount() {
		String query = "SELECT COUNT(*) FROM post";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getPostCountByCategory(String category) {
		String query = "SELECT COUNT(*) FROM post WHERE category = ?";
		return jdbcTemplate.queryForObject(query, new Object[]{category}, Integer.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getPostCountBySubCategory(String category, String subCategory) {
		String query = "SELECT COUNT(*) FROM post WHERE category = ? AND sub_category = ?";
		return jdbcTemplate.queryForObject(query, new Object[]{category, subCategory}, Integer.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getPostCountByTitle(String title) {
		String query = "SELECT COUNT(*) FROM post WHERE title = ?";
		return jdbcTemplate.queryForObject(query, new Object[]{title}, Integer.class);
	}

	@SuppressWarnings("deprecation")
	@Override
	public int getPostCountByWriter(String writer) {
		String query = "SELECT COUNT(*) FROM post WHERE writer = ?";
		return jdbcTemplate.queryForObject(query, new Object[]{writer}, Integer.class);
	}

	@Override
	public int insertPost(Post post) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    int affectedRows = jdbcTemplate.update(con -> {
	    	PreparedStatement ps = con.prepareStatement("INSERT INTO post (category, sub_category, title, content, writer, important) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, post.getCategory());
	        ps.setString(2, post.getSubCategory());
	        ps.setString(3, post.getTitle());
	        ps.setString(4, post.getContent());
	        ps.setString(5, post.getWriter());
	        ps.setBoolean(6, post.isImportant());
	        return ps;
	    }, keyHolder);
	    if (affectedRows == 0) {
	        return 0;
	    }
	    return keyHolder.getKey().intValue();
	}

	@Override
	public int updatePost(Post post) {
	    String sql = "UPDATE post SET category=:category, sub_category=:subCategory, title=:title, content=:content, writer=:writer, " +
	                 "view_count=:viewCount, important=:important " +
	                 "WHERE no=:no";
	    SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(post);
	    return namedParameterJdbcTemplate.update(sql, namedParameters);
	}


	@Override
	public int deletePost(int no) {
	    String sql = "DELETE FROM post WHERE no=:no";
	    MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	    namedParameters.addValue("no", no);
	    return namedParameterJdbcTemplate.update(sql, namedParameters);
	}

	@Override
	public int increaseViewCount(int no) {
        String query = "UPDATE post SET view_count = view_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostByCategoryBetweenNo(String category, int start, int end) {
		String query = "SELECT * FROM post WHERE category = ? ORDER BY created_at DESC LIMIT ?, ?";
		return jdbcTemplate.query(query, new Object[]{category, start, end} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Post> getPostBySubCategoryBetweenNo(String category, String subCategory, int start, int end) {
		String query = "SELECT * FROM post WHERE category = ? AND sub_category = ? ORDER BY created_at DESC LIMIT ?, ?";
		return jdbcTemplate.query(query, new Object[]{category, subCategory, start, end} , rowMapper);
	}

}
