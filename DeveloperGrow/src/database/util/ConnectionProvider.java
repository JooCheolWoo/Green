package database.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionProvider {
	private static final Properties PROPERTIES = new Properties();
	
	static {// static 블럭은 클래스를 사용할때 먼저 실행됨.
		InputStream is = null;
		try {
			ClassLoader loader = ConnectionProvider.class.getClassLoader();
			is = loader.getResourceAsStream("local.properties");
			
			PROPERTIES.load(is);
			
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Connection makeConnection() throws SQLException {
		// 서버의 주소, 아이디, 비밀번호
		String url = PROPERTIES.getProperty("url");
		String id = PROPERTIES.getProperty("id");
		String password = PROPERTIES.getProperty("password");
		
		// DB 연결 객체 : Connection 인터페이스
		Connection conn = null;
		conn = DriverManager.getConnection(url, id, password);
		return conn;
	}
	
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
