package login.signUp;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import database.controllDB.InsertDB;
import database.util.ConnectionProvider;

public class SignUpEvent {

	private SignUpDialog signUpDialog;
	private JLabel lblIdCheck;
	private JLabel lblPwCheck;
	private JLabel lblPwPatternCheck;
	private JLabel lblNickCheck;

	public SignUpEvent(SignUpDialog signUpDialog) {
		this.signUpDialog = signUpDialog;
		lblIdCheck = signUpDialog.getLblIdCheck();
		lblPwCheck = signUpDialog.getLblPwCheck();
		lblPwPatternCheck = signUpDialog.getLblPwPatternCheck();
		lblNickCheck = signUpDialog.getLblNickCheck();
	}
	
	public boolean signUp(String inputId, String inputPw, String inputCheckPw,  String inputNickname) { // 회원가입
		UIManager.put("OptionPane.messageFont", new Font("휴먼둥근헤드라인", Font.PLAIN, 14));
		UIManager.put("OptionPane.buttonFont", new Font("휴먼둥근헤드라인", Font.PLAIN, 12));
		if (idCheck(inputId) && nicknameCheck(inputNickname) 
				&& passwordCheck(inputPw) && passwordIsSame(inputPw, inputCheckPw)) {
			InsertDB.insertUser(inputId, inputPw, inputNickname);
			return true;
		} else if (!idCheck(inputId)) {
			JOptionPane.showMessageDialog(null, "아이디를 확인해 주세요.", "회원가입 경고", JOptionPane.WARNING_MESSAGE);
		} else if (!nicknameCheck(inputNickname)) {
			JOptionPane.showMessageDialog(null, "닉네임을 확인해 주세요.", "회원가입 경고", JOptionPane.WARNING_MESSAGE);
		} else if (!passwordCheck(inputPw)) {
			JOptionPane.showMessageDialog(null, "비밀번호를 확인해 주세요.", "회원가입 경고", JOptionPane.WARNING_MESSAGE);
		} else if (!passwordIsSame(inputPw, inputCheckPw)) {
			JOptionPane.showMessageDialog(null, "비밀번호 일치를 확인해 주세요.", "회원가입 경고", JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
	
	public boolean idCheck(String inputId) {
		if (verifyUserId(inputId) && useridDuplicationCheck(inputId)) {
			lblIdCheck.setText("사용가능");
			return true;
		} else if (!verifyUserId(inputId)) {
			lblIdCheck.setText("형식확인");
		} else {
			lblIdCheck.setText("사용불가");
		}
		return false;
	}

	public boolean nicknameCheck(String inputNickname) {
		if (verifyUserNickName(inputNickname) && userNickNameDuplicationCheck(inputNickname)) {
			lblNickCheck.setText("사용가능");
			return true;
		} else if (!verifyUserNickName(inputNickname)) {
			lblNickCheck.setText("불가");
		} else {
			lblNickCheck.setText("중복");
		}
		return false;
	}
	
	public boolean passwordCheck(String inputPw) {
		if (verifyUserPw(inputPw)) {
			lblPwPatternCheck.setText("사용가능");
			return true;
		} else if (!verifyUserPw(inputPw)) {
			lblPwPatternCheck.setText("비밀번호 형식이 틀립니다");
		} 
		return false;
	}
	
	public boolean passwordIsSame(String inputPw, String inputCheckPw) {
		if (verifyUserPw(inputPw) && inputPw.equals(inputCheckPw)) {
			lblPwCheck.setText("일치");
			return true;
		} else if (!(inputPw.equals(inputCheckPw))) {
			lblPwCheck.setText("불일치");
		}
		return false;		
	}

	private boolean verifyUserId(String userId) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]{1,30}");
		Matcher m = p.matcher(userId);

		return m.matches();
	}

	private boolean verifyUserNickName(String userNickName) {
		Pattern p = Pattern.compile("[a-zA-Z0-9ㄱ-힣]{1,30}");
		Matcher m = p.matcher(userNickName);

		return m.matches();
	}

	public boolean verifyUserPw(String userPw) {
		Pattern p = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
		Matcher m = p.matcher(userPw);

		return m.matches();
	}

	public boolean useridDuplicationCheck(String userId) {
		String sql = "SELECT * FROM team4.user WHERE userId = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, userId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return false; // 아이디 중복이면 false 반환
				} else {
					return true; // 아이디 중복 아니면 true 반환
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean userNickNameDuplicationCheck(String userNickName) {
		String sql = "SELECT * FROM team4.user WHERE userNickname = ?";
		try (Connection conn = ConnectionProvider.makeConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, userNickName);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					return false; // 닉네임 중복이면 false 반환
				} else {
					return true; // 닉네임 중복 아니면 true 반환
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
