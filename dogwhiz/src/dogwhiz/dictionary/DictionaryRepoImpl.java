package dogwhiz.dictionary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository("DictionaryMySQL")
public class DictionaryRepoImpl implements DictionaryRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<DictionaryBoard> rowMapper = new RowMapper<DictionaryBoard>() {
		@Override
		public DictionaryBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
			int no = rs.getInt("no");
			String category = rs.getString("category");
			String title = rs.getString("title");
			String subCategoryD = rs.getString("sub_category");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
			LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
			int viewCount = rs.getInt("view_count");
			int likeCount = rs.getInt("like_count");
			int commentCount = rs.getInt("comment_count");
			
			DictionaryBoard board = new DictionaryBoard();
			board.setNo(no);
			board.setCategory(category);
			board.setTitle(title);
			board.setSubCategoryD(subCategoryD);
			board.setContent(content);
			board.setWriter(writer);
			board.setCreatedAt(createdAt);
			board.setUpdatedAt(updatedAt);
			board.setViewCount(viewCount);
			board.setLikeCount(likeCount);
			board.setCommentCount(commentCount);
			return board;
		}
	};
	

	@Override
	public List<DictionaryBoard> getAllDictionaryDesc() {
		String query = "SELECT * FROM dictionary ORDER BY no DESC";
		return jdbcTemplate.query(query, rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public DictionaryBoard getDictionarywithNo(int no) {
		String query = "SELECT * FROM dictionary WHERE no = ?";
		List<DictionaryBoard> list = jdbcTemplate.query(query, new Object[]{no} , rowMapper);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<DictionaryBoard> getDictionaryWithTitle(String title) {
		String query = "SELECT * FROM dictionary WHERE title LIKE %?% ORDER BY no DESC";
		return jdbcTemplate.query(query, new Object[]{title} , rowMapper);
	}
	
	@Override
	public List<DictionaryBoard> getDictionaryWithSubCategory(String subCategory) {
		String query = "SELECT * FROM dictionary WHERE sub_category ? ORDER BY no DESC";
		return jdbcTemplate.query(query, new Object[]{subCategory} , rowMapper);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<DictionaryBoard> getDictionaryBetweenNoDesc(int start, int end) {
		String query = "SELECT * FROM dictionary ORDER BY no DESC LIMIT ?, ?";
		return jdbcTemplate.query(query, new Object[]{start, end} , rowMapper);
	}

	@Override
	public int getDictionaryCount() {
		String query = "SELECT COUNT(*) FROM dictionary";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public int getNewDictionarytNo() {
		String query = "SELECT MAX(no) FROM dictionary";
		Integer maxPostNo = jdbcTemplate.queryForObject(query, Integer.class);
		return (maxPostNo != null) ? maxPostNo : 1;
	}

	@Override
	public int insertDictionary(DictionaryBoard dictionaryBoard) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    int affectedRows = jdbcTemplate.update(con -> {
	        PreparedStatement ps = con.prepareStatement("INSERT INTO dictionary (category, sub_category, title, content, writer) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, dictionaryBoard.getCategory());
	        ps.setString(2, dictionaryBoard.getSubCategoryD());
	        ps.setString(3, dictionaryBoard.getTitle());
	        ps.setString(4, dictionaryBoard.getContent());
	        ps.setString(5, dictionaryBoard.getWriter());
	        return ps;
	    }, keyHolder);
	    if (affectedRows == 0) {
	        return 0;
	    }
	    return keyHolder.getKey().intValue();
	}

	@Override
	public int updateDictionarywithNo(int no, String subCategory, String title, String content) {
		String query = "UPDATE dictionary SET title = ?, sub_category = ?, content = ? WHERE no = ?";
		LocalDateTime now = LocalDateTime.now();
		return jdbcTemplate.update(query, subCategory, title, content, no);
	}

	@Override
	public int deleteDictionarywithNo(int no) {
		String query = "DELETE FROM dictionary WHERE no = ?";
		return jdbcTemplate.update(query, no);
	}

    @Override
    public int addViewCount(int no) {
        String query = "UPDATE dictionary SET view_count = view_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
    }

    @Override
    public int addLikeCount(int no) {
        String query = "UPDATE dictionary SET like_count = like_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
    }
}
