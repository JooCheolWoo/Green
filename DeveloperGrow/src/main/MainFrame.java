package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.dblist.CigaLog;
import database.dblist.Project;
import database.dblist.Rank;
import database.dblist.SkillList;
import database.dblist.User;
import database.dblist.UserInfo;
import database.dblist.UserProject;
import database.dblist.UserSkill;
import guiDesign.ImagePanel;
import guiDesign.Methods;
import login.LoginFrame;
import main.active.ActiveDialog;
import main.active.ActiveEventTimer;
import main.minigame.GameDialog;
import main.project.ProjectDialog;
import main.project.ProjectEventImpl;
import main.ranking.RankingDialog;
import main.setting.SettingDialog;
import main.store.StoreDialog;
import progressbar.ProgressbarEvent;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	// 게임속도
	int gameSpeed = 1;

	// timer 정지 및 시작
	private boolean timeGo = true;
	private boolean projectGo = true;

	// 메인페널
	private ImagePanel Mainpnl;

	// 사운드
	private static Clip clip;
	private JButton soundbtn;

	// 활동버튼
	private ActiveEventTimer activeEventTimer = new ActiveEventTimer(this);
	private JButton activitybtn;

	// 메인시간
	private GameControllerImpl gameControllerImpl = new GameControllerImpl(this);
	private JLabel datelbl;
	private JLabel hourlbl;
	private JLabel minutelbl;

	// 상점
	private StoreDialog storeFrame = null;

	// 프로젝트
	private ProjectDialog projectFrame = null;
	private ProjectEventImpl projectEventImpl = new ProjectEventImpl(this);
	private JLabel nowRatinglbl;
	private JLabel nowProjectlbl;
	private JLabel projectHour;
	private JLabel projectMinute;
	private int nowProjectId = 0;
	private int deadLine = 2880;
	private JLabel projectDeadLine;

	// 정보
	private JLabel levellbl;
	private JLabel numOfcigalbl;

	// progressbar
	private ProgressbarEvent pb = new ProgressbarEvent(this);
	private JProgressBar hpbar;
	private JProgressBar expbar;
	private JProgressBar stressbar;
	private JProgressBar healthbar;

	// DB
	private List<UserInfo> userInfoList;
	private List<SkillList> skillList;
	private List<UserSkill> userSkillList;
	private List<Project> projectList;
	private List<UserProject> userProjectList;
	private List<User> userList;
	private List<CigaLog> cigaLogList;
	private List<Rank> userRankList;
	private UserInfo userInfo;
	private Rank userRank;
	private int infoId;
	private int userId;
	private int score;

	// 미니게임
	GameDialog gameDialog = null;

	private JButton taskbtn;

	public MainFrame(int userId) {
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		taskbtn.addActionListener(listener);
		taskbtn.setActionCommand("일번동작");
		taskbtn.addActionListener(listener);
		taskbtn.setActionCommand("이번동작");
		taskbtn.addActionListener(listener);
		taskbtn.setActionCommand("삼번동작");
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		ClassLoader classLoader = getClass().getClassLoader();
		URL URLmix = classLoader.getResource("music/mix.wav");
		sound(URLmix);

		setBounds(100, 100, 1200, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Mainpnl = new ImagePanel(Methods.converImage(getClass(), "images/back_img/background.png"));
		Mainpnl.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Mainpnl);
		Mainpnl.setLayout(null);

		activeEventTimer.getCharacters().defaultCharacter();

		ImageIcon sound = Methods.convertToResizeIcon(getClass(), "images/btn_img/sound.png", 50, 50);
		ImageIcon mute = Methods.convertToResizeIcon(getClass(), "images/btn_img/mute.png", 50, 50);

		JPanel statuspnl = new JPanel();
		statuspnl.setBounds(46, 613, 934, 114);
		Mainpnl.add(statuspnl);
		statuspnl.setLayout(null);
		statuspnl.setBackground(new Color(255, 255, 255, 100));

		JPanel currentcigapnl = new JPanel();
		currentcigapnl.setBounds(22, 10, 225, 42);
		statuspnl.add(currentcigapnl);
		currentcigapnl.setOpaque(false);
		currentcigapnl.setLayout(null);

		JLabel currentcigalbl = new JLabel("담배  X");
		currentcigalbl.setIcon(Methods.convertToIcon(getClass(), "images/rank_img/ciga.png"));

		currentcigalbl.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		currentcigalbl.setBounds(12, 0, 117, 43);
		currentcigapnl.add(currentcigalbl);

		numOfcigalbl = new JLabel("00");
		numOfcigalbl.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		numOfcigalbl.setBounds(124, 5, 89, 33);
		currentcigapnl.add(numOfcigalbl);

		JPanel stresspnl = new JPanel();
		stresspnl.setBounds(294, 40, 628, 30);
		statuspnl.add(stresspnl);
		stresspnl.setOpaque(false);
		stresspnl.setLayout(null);

		JLabel stresslbl = new JLabel("스트레스");
		stresslbl.setHorizontalAlignment(SwingConstants.LEFT);
		stresslbl.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		stresslbl.setBounds(39, 5, 85, 22);
		stresspnl.add(stresslbl);

		stressbar = new JProgressBar();
		stressbar.setFont(new Font("휴먼편지체", Font.PLAIN, 12));
		stressbar.setValue(10);
		stressbar.setStringPainted(true);
		stressbar.setForeground(new Color(139, 0, 0));
		stressbar.setBounds(136, 5, 480, 22);
		stresspnl.add(stressbar);

		JPanel developlvpnl = new JPanel();
		developlvpnl.setBounds(22, 62, 225, 42);
		statuspnl.add(developlvpnl);
		developlvpnl.setOpaque(false);
		developlvpnl.setLayout(null);

		JLabel lblNewLabel = new JLabel("개발능력Lv");
		lblNewLabel.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 5, 110, 32);
		developlvpnl.add(lblNewLabel);

		levellbl = new JLabel("00");
		levellbl.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		levellbl.setBounds(124, 5, 89, 33);
		developlvpnl.add(levellbl);

		JPanel healthpnl = new JPanel();
		healthpnl.setLayout(null);
		healthpnl.setOpaque(false);
		healthpnl.setBounds(294, 75, 628, 30);
		statuspnl.add(healthpnl);

		JLabel healthlbl = new JLabel("건강");
		healthlbl.setHorizontalAlignment(SwingConstants.LEFT);
		healthlbl.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		healthlbl.setBounds(39, 5, 85, 22);
		healthpnl.add(healthlbl);

		healthbar = new JProgressBar();
		healthbar.setFont(new Font("휴먼편지체", Font.PLAIN, 12));
		healthbar.setValue(100);
		healthbar.setStringPainted(true);
		healthbar.setForeground(new Color(0, 128, 0));
		healthbar.setBounds(136, 5, 480, 22);
		healthpnl.add(healthbar);

		JPanel hppnl = new JPanel();
		hppnl.setBounds(294, 5, 628, 30);
		statuspnl.add(hppnl);
		hppnl.setOpaque(false);
		hppnl.setBackground(new Color(255, 0, 0, 0));
		hppnl.setLayout(null);

		JLabel hplbl = new JLabel("HP");
		hplbl.setHorizontalAlignment(SwingConstants.LEFT);
		hplbl.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		hplbl.setBounds(39, 5, 85, 22);
		hppnl.add(hplbl);

		hpbar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		hpbar.setFont(new Font("휴먼편지체", Font.PLAIN, 12));
		hpbar.setForeground(Color.RED);
		hpbar.setStringPainted(true);
		hpbar.setValue(100);
		hpbar.setBounds(136, 5, 480, 22);
		hppnl.add(hpbar);

		JButton btnSpeed = new JButton(Methods.convertToResizeIcon(getClass(), "images/btn_img/speed1.png", 60, 50));
		btnSpeed.setForeground(Color.RED);
		btnSpeed.setBackground(new Color(0, 0, 0, 0));
		btnSpeed.setOpaque(false);
		btnSpeed.setBorderPainted(false);
		btnSpeed.setBounds(290, 10, 60, 50);
		Mainpnl.add(btnSpeed);
		btnSpeed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameSpeed == 5) {
					gameSpeed = 1;
				} else {
					gameSpeed++;
				}
				if (gameSpeed == 1) {
					btnSpeed.setIcon(Methods.convertToResizeIcon(getClass(), "images/btn_img/speed1.png", 60, 50));
				} else if (gameSpeed == 2) {
					btnSpeed.setIcon(Methods.convertToResizeIcon(getClass(), "images/btn_img/speed2.png", 60, 50));
				} else if (gameSpeed == 3) {
					btnSpeed.setIcon(Methods.convertToResizeIcon(getClass(), "images/btn_img/speed3.png", 60, 50));
				} else if (gameSpeed == 4) {
					btnSpeed.setIcon(Methods.convertToResizeIcon(getClass(), "images/btn_img/speed4.png", 60, 50));
				} else if (gameSpeed == 5) {
					btnSpeed.setIcon(Methods.convertToResizeIcon(getClass(), "images/btn_img/speed5.png", 60, 50));
				}
			}
		});
		soundbtn = new JButton(sound);
		soundbtn.setBounds(978, 28, 50, 50);
		soundbtn.setBackground(new Color(0, 0, 0, 0));
		soundbtn.setBorderPainted(false);
		soundbtn.setOpaque(false);
		soundbtn.setFocusable(false);
		getContentPane().add(soundbtn);
		soundbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (soundbtn.getIcon() == sound) {
					soundbtn.setIcon(mute);
					clip.stop();
				} else if (soundbtn.getIcon() == mute) {
					soundbtn.setIcon(sound);
					clip.start();
				}
			}
		});

		expbar = new JProgressBar();
		expbar.setValue(20);
		expbar.setStringPainted(true);
		expbar.setForeground(new Color(30, 144, 255));
		expbar.setFont(new Font("휴먼편지체", Font.PLAIN, 12));
		expbar.setBounds(95, 737, 861, 20);
		Mainpnl.add(expbar);

		JLabel lblNewLabel_2 = new JLabel("exp");
		lblNewLabel_2.setFont(new Font("휴먼편지체", Font.BOLD, 20));
		lblNewLabel_2.setBounds(46, 728, 37, 29);
		Mainpnl.add(lblNewLabel_2);

		JPanel date = new JPanel();
		date.setBounds(12, 10, 135, 45);
		Mainpnl.add(date);
		date.setLayout(null);
		date.setOpaque(false);

		datelbl = new JLabel("00일차");
		datelbl.setHorizontalAlignment(SwingConstants.CENTER);
		datelbl.setFont(new Font("HY목각파임B", Font.BOLD, 30));
		datelbl.setBounds(0, 0, 135, 45);
		date.add(datelbl);

		JPanel currentTime = new JPanel();
		currentTime.setBounds(147, 10, 135, 45);
		Mainpnl.add(currentTime);
//		currentTime.setBorder(new LineBorder(Color.BLACK, 2));
		currentTime.setOpaque(false);
		currentTime.setBackground(new Color(255, 0, 0, 0));
		currentTime.setLayout(null);

		hourlbl = new JLabel("00");
		hourlbl.setHorizontalAlignment(SwingConstants.CENTER);

		hourlbl.setFont(new Font("HY목각파임B", Font.BOLD, 30));
		hourlbl.setBounds(0, 0, 60, 45);
		currentTime.add(hourlbl);

		minutelbl = new JLabel("00");
		minutelbl.setHorizontalAlignment(SwingConstants.CENTER);
		minutelbl.setFont(new Font("HY목각파임B", Font.BOLD, 28));
		minutelbl.setBounds(75, 0, 60, 45);
		currentTime.add(minutelbl);

		JLabel colonlbl = new JLabel(":");
		colonlbl.setHorizontalAlignment(SwingConstants.CENTER);
		colonlbl.setFont(new Font("Algerian", Font.BOLD, 30));
		colonlbl.setBounds(60, 0, 15, 45);
		currentTime.add(colonlbl);

		JButton rankingbtn = new JButton();
		rankingbtn.setBounds(1051, 140, 90, 89);
		rankingbtn.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/rankBtnImage.png"));
		Mainpnl.add(rankingbtn);
		rankingbtn.setBorderPainted(false);
		rankingbtn.setOpaque(false);
		rankingbtn.setBackground(new Color(255, 0, 0, 0));
		rankingbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeGo = false;
				RankingDialog rankingFrame = new RankingDialog(MainFrame.this.getX(), MainFrame.this.getY(),
						MainFrame.this);
				rankingFrame.showGUI();
			}
		});

		JButton settingbtn = new JButton();
		settingbtn.setBounds(1051, 10, 90, 89);
		settingbtn.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/settingBtnImage.png"));
		Mainpnl.add(settingbtn);
		settingbtn.setOpaque(false);
		settingbtn.setBorderPainted(false);
		settingbtn.setBackground(new Color(255, 0, 0, 0));
		settingbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timeGo = false;
				SettingDialog settingFrame = new SettingDialog(MainFrame.this.getX(), MainFrame.this.getY(),
						MainFrame.this);
				settingFrame.showGUI();
				if (settingFrame.getLoginFrame()) {
					new LoginFrame();
					dispose();
				}
			}
		});

		JButton storebtn = new JButton();
		storebtn.setBounds(1051, 275, 90, 89);
		storebtn.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/StoreBtnImage.png"));
		Mainpnl.add(storebtn);
		storebtn.setOpaque(false);
		storebtn.setBackground(new Color(255, 0, 0, 0));
		storebtn.setBorderPainted(false);
		storebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timeGo = false;
				if (storeFrame == null) {
					storeFrame = new StoreDialog(MainFrame.this);
				}
				storeFrame.setBounds(MainFrame.this.getX() + 7, MainFrame.this.getY() + 30, 1185, 762);
				storeFrame.showGUI();
			}
		});

		JButton gamebtn = new JButton();
		gamebtn.setBounds(1051, 395, 90, 81);
		gamebtn.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/miniGameBtnImage.png"));
		Mainpnl.add(gamebtn);
		gamebtn.setOpaque(false);
		gamebtn.setBorderPainted(false);
		gamebtn.setBackground(new Color(255, 0, 0, 0));
		gamebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				timeGo = false;
				gameDialog = new GameDialog(MainFrame.this.getX(), MainFrame.this.getY(), MainFrame.this);
				gameDialog.showGUI();
			}
		});

		taskbtn = new JButton();
		taskbtn.setHorizontalAlignment(SwingConstants.LEFT);
		taskbtn.setBounds(1042, 525, 99, 81);
		;
		taskbtn.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/projectBtnImage.png"));
		taskbtn.setOpaque(false);
		taskbtn.setBackground(new Color(255, 0, 0, 0));
		taskbtn.setBorderPainted(false);
		taskbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				timeGo = false;
				projectFrame = new ProjectDialog(MainFrame.this);
				projectFrame.setBounds(MainFrame.this.getX() + 7, MainFrame.this.getY() + 30, 1185, 762);
				projectFrame.showGUI();
			}
		});
		Mainpnl.add(taskbtn);

		activitybtn = new JButton();
		activitybtn.setBounds(1058, 633, 83, 89);
		activitybtn.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/activeBtnImage.png"));
		Mainpnl.add(activitybtn);
		activitybtn.setOpaque(false);
		activitybtn.setBorderPainted(false);
		activitybtn.setBackground(new Color(255, 0, 0, 0));
		activitybtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActiveDialog activeFrame = new ActiveDialog(MainFrame.this.getX(), MainFrame.this.getY(),
						MainFrame.this);
				activeFrame.showGUI();
			}
		});

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0, 50));
		panel.setBounds(12, 207, 263, 157);
		Mainpnl.add(panel);
		panel.setLayout(null);

		JLabel prjectTitlelbl = new JLabel("<현재 진행중인 Project>");
		prjectTitlelbl.setBounds(18, 10, 227, 24);
		prjectTitlelbl.setFont(new Font("HY목각파임B", Font.BOLD, 16));
		panel.add(prjectTitlelbl);

		nowProjectlbl = new JLabel("");
		nowProjectlbl.setFont(new Font("HY엽서L", Font.BOLD, 14));
		nowProjectlbl.setBounds(18, 44, 227, 24);
		panel.add(nowProjectlbl);

		JLabel ratingText = new JLabel("진행률 : ");
		ratingText.setFont(new Font("HY엽서L", Font.BOLD, 14));
		ratingText.setBounds(18, 78, 72, 15);
		panel.add(ratingText);

		nowRatinglbl = new JLabel("0");
		nowRatinglbl.setFont(new Font("HY엽서L", Font.BOLD, 14));
		nowRatinglbl.setHorizontalAlignment(SwingConstants.CENTER);
		nowRatinglbl.setBounds(84, 78, 58, 15);
		panel.add(nowRatinglbl);

		JLabel lblNewLabel_1 = new JLabel("%");
		lblNewLabel_1.setFont(new Font("휴먼편지체", Font.BOLD, 14));
		lblNewLabel_1.setBounds(154, 78, 21, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_3 = new JLabel("남은 시간: ");
		lblNewLabel_3.setFont(new Font("HY엽서L", Font.BOLD, 14));
		lblNewLabel_3.setBounds(18, 105, 83, 15);
		panel.add(lblNewLabel_3);

		projectHour = new JLabel("00");
		projectHour.setFont(new Font("HY엽서L", Font.BOLD, 14));
		projectHour.setBounds(113, 103, 29, 17);
		panel.add(projectHour);

		JLabel lblNewLabel_4 = new JLabel(":");
		lblNewLabel_4.setFont(new Font("HY엽서L", Font.BOLD, 14));
		lblNewLabel_4.setBounds(143, 103, 11, 15);
		panel.add(lblNewLabel_4);

		projectMinute = new JLabel("00");
		projectMinute.setFont(new Font("HY엽서L", Font.BOLD, 14));
		projectMinute.setBounds(154, 103, 29, 17);
		panel.add(projectMinute);

		JLabel lblNewLabel_3_1 = new JLabel("마감 시간: ");
		lblNewLabel_3_1.setFont(new Font("HY엽서L", Font.BOLD, 14));
		lblNewLabel_3_1.setBounds(18, 130, 83, 15);
		panel.add(lblNewLabel_3_1);

		projectDeadLine = new JLabel("0000");
		projectDeadLine.setFont(new Font("HY엽서L", Font.BOLD, 14));
		projectDeadLine.setBounds(113, 130, 62, 17);
		panel.add(projectDeadLine);

		JLabel de = new JLabel("분");
		de.setFont(new Font("HY엽서L", Font.BOLD, 14));
		de.setBounds(164, 130, 29, 17);
		panel.add(de);

		gameControllerImpl.readyGame(userId);
		gameControllerImpl.applyDB();
		gameControllerImpl.timeController();
		pb.controllPB_default();
	}

	public void showGUI() {
		setVisible(true);
	}

	public void sound(URL file) {
		try {
			AudioInputStream ais1 = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais1);
			clip.start();
			clip.loop(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JButton getActivitybtn() {
		return activitybtn;
	}

	public void stopSound() {
		clip.stop();
		clip.close();
	}

	public JLabel getHourlbl() {
		return hourlbl;
	}

	public JLabel getMinutelbl() {
		return minutelbl;
	}

	public JLabel getDatelbl() {
		return datelbl;
	}

	public JLabel getNowProjectlbl() {
		return nowProjectlbl;
	}

	public JLabel getNowRatinglbl() {
		return nowRatinglbl;
	}

	public JLabel getProjectHour() {
		return projectHour;
	}

	public JLabel getProjectMinute() {
		return projectMinute;
	}

	public JProgressBar getHpbar() {
		return hpbar;
	}

	public JProgressBar getExpbar() {
		return expbar;
	}

	public JProgressBar getStessbar() {
		return stressbar;
	}

	public JProgressBar getHealthbar() {
		return healthbar;
	}

	public ProgressbarEvent getPb() {
		return pb;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public List<UserSkill> getUserSkillList() {
		return userSkillList;
	}

	public List<SkillList> getSkillList() {
		return skillList;
	}

	public void setUserSkillList(List<UserSkill> userSkillList) {
		this.userSkillList = userSkillList;
	}

	public List<UserProject> getUserProjectList() {
		return userProjectList;
	}

	public void setUserProjectList(List<UserProject> userProjectList) {
		this.userProjectList = userProjectList;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<Project> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<Project> projectList) {
		this.projectList = projectList;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public int getInfoId() {
		return infoId;
	}

	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}

	public void setCigaLogList(List<CigaLog> cigaLogList) {
		this.cigaLogList = cigaLogList;
	}

	public JLabel getLevellbl() {
		return levellbl;
	}

	public JLabel getNumOfcigalbl() {
		return numOfcigalbl;
	}

	public void setSkillList(List<SkillList> skillList) {
		this.skillList = skillList;
	}

	public JProgressBar getStressbar() {
		return stressbar;
	}

	public ProjectEventImpl getProjectEventImpl() {
		return projectEventImpl;
	}

	public int getNowProjectId() {
		return nowProjectId;
	}

	public void setNowProjectId(int nowProjectId) {
		this.nowProjectId = nowProjectId;
	}

	public List<Rank> getUserRankList() {
		return userRankList;
	}

	public void setUserRankList(List<Rank> userRankList) {
		this.userRankList = userRankList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Rank getUserRank() {
		return userRank;
	}

	public void setUserRank(Rank userRank) {
		this.userRank = userRank;
	}

	public GameControllerImpl getGameControllerImpl() {
		return gameControllerImpl;
	}

	public int getGameSpeed() {
		return gameSpeed;
	}

	public boolean isTimeGo() {
		return timeGo;
	}

	public void setTimeGo(boolean timeGo) {
		this.timeGo = timeGo;
	}

	public ActiveEventTimer getActiveEventTimer() {
		return activeEventTimer;
	}

	public boolean isProjectGo() {
		return projectGo;
	}

	public void setProjectGo(boolean projectGo) {
		this.projectGo = projectGo;
	}

	public int getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(int deadLine) {
		this.deadLine = deadLine;
	}

	public JLabel getProjectDeadLine() {
		return projectDeadLine;
	}

	public JButton getTaskbtn() {
		return taskbtn;
	}
	
}
