package dogwhiz.community;

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

@Repository("AnnoucementMySQL")
public class CommunityRepoImpl implements CommunityRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<CommunityBoard> rowMapper = new RowMapper<CommunityBoard>() {
		@Override
		public CommunityBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
			int no = rs.getInt("no");
			String category = rs.getString("category");
			String title = rs.getString("title");
			String subCategory = rs.getString("sub_category");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
			LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
			int viewCount = rs.getInt("view_count");
			int likeCount = rs.getInt("like_count");
			int commentCount = rs.getInt("comment_count");
			
			CommunityBoard board = new CommunityBoard();
			board.setNo(no);
			board.setCategory(category);
			board.setTitle(title);
			board.setSubCategory(subCategory);
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
	public List<CommunityBoard> getAllcommunityDesc() {
		String query = "SELECT * FROM community ORDER BY no DESC";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<CommunityBoard>(CommunityBoard.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public CommunityBoard getCommunitywithNo(int no) {
		String query = "SELECT * FROM community WHERE no = ?";
		List<CommunityBoard> list = jdbcTemplate.query(query, new Object[]{no} , new BeanPropertyRowMapper<>(CommunityBoard.class));
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CommunityBoard> getCommunityWithTitle(String title) {
		String query = "SELECT * FROM community WHERE title LIKE %?% ORDER BY no DESC";
		return jdbcTemplate.query(query, new Object[]{title} , new BeanPropertyRowMapper<>(CommunityBoard.class));
	}
	
	@Override
	public List<CommunityBoard> getCommunityWithSubCategory(String subCategory) {
		String query = "SELECT * FROM community WHERE sub_category ? ORDER BY no DESC";
		return jdbcTemplate.query(query, new Object[]{subCategory} , new BeanPropertyRowMapper<>(CommunityBoard.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<CommunityBoard> getCommunityBetweenNoDesc(int start, int end) {
		String query = "SELECT * FROM community ORDER BY no DESC LIMIT ?, ?";
		return jdbcTemplate.query(query, new Object[]{start, end} , new BeanPropertyRowMapper<>(CommunityBoard.class));
	}

	@Override
	public int getCommunityCount() {
		String query = "SELECT COUNT(*) FROM community";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public int getNewCommunitytNo() {
		String query = "SELECT MAX(no) FROM community";
		Integer maxPostNo = jdbcTemplate.queryForObject(query, Integer.class);
		return (maxPostNo != null) ? maxPostNo : 1;
	}

	@Override
	public int insertCommunity(CommunityBoard communityBoard) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    int affectedRows = jdbcTemplate.update(con -> {
	        PreparedStatement ps = con.prepareStatement("INSERT INTO community (category, sub_category, title, content, writer) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, communityBoard.getCategory());
	        ps.setString(2, communityBoard.getSubCategory());
	        ps.setString(3, communityBoard.getTitle());
	        ps.setString(4, communityBoard.getContent());
	        ps.setString(5, communityBoard.getWriter());
	        return ps;
	    }, keyHolder);
	    if (affectedRows == 0) {
	        return 0;
	    }
	    return keyHolder.getKey().intValue();
	}

	@Override
	public int updateCommunitywithNo(int no, String subCategory, String title, String content) {
		String query = "UPDATE community SET title = ?, sub_category = ?, content = ? WHERE no = ?";
		LocalDateTime now = LocalDateTime.now();
		return jdbcTemplate.update(query, subCategory, title, content, no);
	}

	@Override
	public int deleteCommunitywithNo(int no) {
		String query = "DELETE FROM community WHERE no = ?";
		return jdbcTemplate.update(query, no);
	}

    @Override
    public int addViewCount(int no) {
        String query = "UPDATE community SET view_count = view_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
    }

    @Override
    public int addLikeCount(int no) {
        String query = "UPDATE community SET like_count = like_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
    }
}
