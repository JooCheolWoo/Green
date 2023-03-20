package main.project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import main.MainFrame;

public class ProjectPanel extends JPanel implements MouseListener {
	
	private MainFrame mainFrame;
	ProjectDialog projectDialog;
	
	private JLabel rankLabel;
	private JLabel projectName;
	private JLabel timeTakenlbl;
	private JLabel completelbl;
	private JPanel completepnl;
	private JPanel proceedingpnl;
	private JLabel proceedinglbl;
	private JLabel rewardlbl;
	
	int index = 0;
	
	private boolean proceeding = false;
	private boolean complete = false;
	private boolean selectable = false;

	private JLabel selectablelbl;

	private JPanel selectablepnl;
	
	public ProjectPanel(MainFrame mainFrame, ProjectDialog projectDialog) {
		this.mainFrame = mainFrame;
		this.projectDialog = projectDialog;
		
		EtchedBorder eborder = new EtchedBorder(EtchedBorder.RAISED);
		setBorder(eborder);
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 540, 100);
		setLayout(null);
		
		completepnl = new JPanel();
		completepnl.setBounds(0, 0, 540, 100);
		completepnl.setBackground(new Color(100, 0, 0, 100));
		add(completepnl);
		completepnl.setVisible(false);
		completepnl.setLayout(null);
		
		completelbl = new JLabel("완료");
		completelbl.setForeground(Color.RED);
		completelbl.setFont(new Font("HY목각파임B", Font.BOLD, 40));
		completelbl.setHorizontalAlignment(SwingConstants.CENTER);
		completelbl.setBounds(236, 24, 84, 47);
		completepnl.add(completelbl);
		
		proceedingpnl = new JPanel();
		proceedingpnl.setBounds(0, 0, 540, 100);
		proceedingpnl.setBackground(new Color(0, 0, 100, 100));
		add(proceedingpnl);
		proceedingpnl.setVisible(false);
		proceedingpnl.setLayout(null);
		
		proceedinglbl = new JLabel("진행중");
		proceedinglbl.setForeground(Color.GREEN);
		proceedinglbl.setFont(new Font("HY목각파임B", Font.BOLD, 40));
		proceedinglbl.setHorizontalAlignment(SwingConstants.CENTER);
		proceedinglbl.setBounds(209, 24, 136, 47);
		proceedingpnl.add(proceedinglbl);
			
		selectablepnl = new JPanel();
		selectablepnl.setBounds(0, 0, 540, 100);
		selectablepnl.setBackground(new Color(100, 100, 100, 100));
		add(selectablepnl);
		selectablepnl.setVisible(true);
		selectablepnl.setLayout(null);
		
		selectablelbl = new JLabel("이전 과제를 완료해야 진행 가능합니다.");
		selectablelbl.setForeground(Color.black);
		selectablelbl.setFont(new Font("HY목각파임B", Font.BOLD, 20));
		selectablelbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectablelbl.setBounds(65, 24, 404, 47);
		selectablepnl.add(selectablelbl);
		
		rankLabel = new JLabel("1주차");
		rankLabel.setBorder(eborder);
		rankLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		rankLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rankLabel.setForeground(Color.BLACK);
		rankLabel.setBackground(Color.WHITE);
		rankLabel.setOpaque(true);
		rankLabel.setBounds(12, 10, 80, 80);
		add(rankLabel);
		
		projectName = new JLabel("업무이름");
		projectName.setBorder(eborder);
		projectName.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		projectName.setForeground(Color.BLACK);
		projectName.setBackground(Color.WHITE);
		projectName.setOpaque(true);
		projectName.setBounds(104, 10, 424, 28);
		add(projectName);
		
		JLabel timeLabel = new JLabel("소요시간");
		timeLabel.setBorder(eborder);
		timeLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setForeground(Color.BLACK);
		timeLabel.setBackground(Color.WHITE);
		timeLabel.setOpaque(true);
		timeLabel.setBounds(281, 48, 91, 42);
		add(timeLabel);
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL URLCigaImage = classLoader.getResource("images/rank_img/ciga.png");
		ImageIcon cigaImageIcon = new ImageIcon(URLCigaImage);
		
		rewardlbl = new JLabel("100");
		rewardlbl.setBorder(eborder);
		rewardlbl.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		rewardlbl.setHorizontalAlignment(SwingConstants.CENTER);
		rewardlbl.setForeground(Color.BLACK);
		rewardlbl.setBackground(Color.WHITE);
		rewardlbl.setOpaque(true);
		rewardlbl.setBounds(201, 48, 68, 42);
		add(rewardlbl);
		
		JLabel scoreTextLabel = new JLabel("보상EXp");
		scoreTextLabel.setBorder(eborder);
		scoreTextLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		scoreTextLabel.setOpaque(true);
		scoreTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTextLabel.setForeground(Color.BLACK);
		scoreTextLabel.setBackground(Color.WHITE);
		scoreTextLabel.setBounds(104, 48, 85, 42);
		add(scoreTextLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(eborder);
		panel.setBounds(384, 48, 144, 42);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		timeTakenlbl = new JLabel("24");
		timeTakenlbl.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(timeTakenlbl);
		timeTakenlbl.setBorder(null);
		timeTakenlbl.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		timeTakenlbl.setForeground(Color.BLACK);
		timeTakenlbl.setBackground(Color.WHITE);
		timeTakenlbl.setOpaque(true);
		
		addMouseListener(this);
	}

	@Override
	public Dimension preferredSize() {
		return new Dimension(540, 100);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (selectable && !complete && !proceeding) {
			mainFrame.getNowRatinglbl().setText("00");
			setProceeding(true);
			getProceedingpnl().setVisible(true);
			String str = mainFrame.getProjectList().get(index).getProjectName();
			mainFrame.getNowProjectlbl().setText(str);
			int time = mainFrame.getProjectList().get(index).getTime();
			mainFrame.getProjectHour().setText(String.valueOf(time));
			mainFrame.revalidate();
			mainFrame.repaint();
			mainFrame.getUserProjectList().get(index).setProceeding(true);
			mainFrame.getProjectEventImpl().projectTimeControll(time, index);	
			projectDialog.dispose();
			mainFrame.setTimeGo(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if (selectable && !complete && !proceeding) {
			setBackground(Color.pink);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (selectable && !complete && !proceeding) {
			setBackground(Color.LIGHT_GRAY);
		}
	}
	
	public JLabel getRankLabel() {
		return rankLabel;
	}
	
	public JLabel getProjectName() {
		return projectName;
	}
	
	public JLabel getTimeTakenlbl() {
		return timeTakenlbl;
	}
	
	public JPanel getCompletepnl() {
		return completepnl;
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete(boolean complete) {
		this.complete = complete;
	}
	

	public boolean isProceeding() {
		return proceeding;
	}

	public void setProceeding(boolean proceeding) {
		this.proceeding = proceeding;
	}

	public JPanel getProceedingpnl() {
		return proceedingpnl;
	}

	public void setProceedingpnl(JPanel proceedingpnl) {
		this.proceedingpnl = proceedingpnl;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}

	public JLabel getRewardlbl() {
		return rewardlbl;
	}

	public void setRewardlbl(JLabel rewardlbl) {
		this.rewardlbl = rewardlbl;
	}

	public JPanel getSelectablepnl() {
		return selectablepnl;
	}
	
}
