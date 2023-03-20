package dogwhiz.announcement;

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
public class AnnouncementRepoImpl implements AnnouncementRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<AnnouncementBoard> rowMapper = new RowMapper<AnnouncementBoard>() {
		@Override
		public AnnouncementBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
			int no = rs.getInt("no");
			String category = rs.getString("category");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
			LocalDateTime updatedAt = rs.getTimestamp("updated_at").toLocalDateTime();
			int viewCount = rs.getInt("view_count");
			int likeCount = rs.getInt("like_count");
			int commentCount = rs.getInt("comment_count");
			Boolean important = rs.getBoolean("important");
			
			AnnouncementBoard board = new AnnouncementBoard();
			board.setNo(no);
			board.setCategory(category);
			board.setTitle(title);
			board.setContent(content);
			board.setWriter(writer);
			board.setCreatedAt(createdAt);
			board.setUpdatedAt(updatedAt);
			board.setViewCount(viewCount);
			board.setLikeCount(likeCount);
			board.setCommentCount(commentCount);
			board.setImportant(important);
			return board;
		}
	};
	

	@Override
	public List<AnnouncementBoard> getAllAnnouncementDesc() {
		String query = "SELECT * FROM announcement ORDER BY no DESC";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<AnnouncementBoard>(AnnouncementBoard.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public AnnouncementBoard getAnnouncementwithNo(int no) {
		String query = "SELECT * FROM announcement WHERE no = ?";
		List<AnnouncementBoard> list = jdbcTemplate.query(query, new Object[]{no} , new BeanPropertyRowMapper<>(AnnouncementBoard.class));
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<AnnouncementBoard> getAnnouncementWithTitle(String title) {
		String query = "SELECT * FROM announcement WHERE title LIKE %?% ORDER BY no DESC";
		return jdbcTemplate.query(query, new Object[]{title} , new BeanPropertyRowMapper<>(AnnouncementBoard.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<AnnouncementBoard> getAnnouncementIsImportant(Boolean important) {
		String query = "SELECT * FROM announcement WHERE important = ? ORDER BY no DESC";
		return jdbcTemplate.query(query, new Object[]{important} , new BeanPropertyRowMapper<>(AnnouncementBoard.class));
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<AnnouncementBoard> getAnnouncementBetweenNoDesc(int start, int end) {
		String query = "SELECT * FROM announcement ORDER BY no DESC LIMIT ?, ?";
		return jdbcTemplate.query(query, new Object[]{start, end} , new BeanPropertyRowMapper<>(AnnouncementBoard.class));
	}

	@Override
	public int getAnnouncementCount() {
		String query = "SELECT COUNT(*) FROM announcement";
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	@Override
	public int getNewAnnouncementNo() {
		String query = "SELECT MAX(no) FROM announcement";
		Integer maxPostNo = jdbcTemplate.queryForObject(query, Integer.class);
		return (maxPostNo != null) ? maxPostNo : 1;
	}

	@Override
	public int insertAnnouncement(AnnouncementBoard announcementBoard) {
	    KeyHolder keyHolder = new GeneratedKeyHolder();
	    int affectedRows = jdbcTemplate.update(con -> {
	        PreparedStatement ps = con.prepareStatement("INSERT INTO announcement (category, title, content, writer, important) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, announcementBoard.getCategory());
	        ps.setString(2, announcementBoard.getTitle());
	        ps.setString(3, announcementBoard.getContent());
	        ps.setString(4, announcementBoard.getWriter());
	        ps.setBoolean(5, announcementBoard.isImportant());
	        return ps;
	    }, keyHolder);
	    if (affectedRows == 0) {
	        return 0;
	    }
	    return keyHolder.getKey().intValue();
	}

	@Override
	public int updateAnnouncementwithNo(int no, String title, String content) {
		String query = "UPDATE announcement SET title = ?, content = ? WHERE no = ?";
		LocalDateTime now = LocalDateTime.now();
		return jdbcTemplate.update(query, title, content, no);
	}

	@Override
	public int deleteAnnouncementwithNo(int no) {
		String query = "DELETE FROM announcement WHERE no = ?";
		return jdbcTemplate.update(query, no);
	}

    @Override
    public int addViewCount(int no) {
        String query = "UPDATE announcement SET view_count = view_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
    }

    @Override
    public int addLikeCount(int no) {
        String query = "UPDATE announcement SET like_count = like_count + 1 WHERE no = ?";
        return jdbcTemplate.update(query, no);
    }
}
