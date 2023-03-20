package main.project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import database.dblist.Project;
import database.dblist.UserProject;
import main.MainFrame;

public class ProjectDialog extends JDialog {

	private JPanel contentPane;
	private MainFrame mainFrame;
	private ProjectPanel[] pjs;
	private List<Project> projectList;
	private List<UserProject> userProjectList;
	

	public ProjectDialog(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	
		projectList = mainFrame.getProjectList();
		userProjectList = mainFrame.getUserProjectList();
		
		setUndecorated(true);
		setModal(true);
		setBackground(new Color(0, 0, 0, 100));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setOpaque(false);
		contentPane.setBackground(new Color(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel rankTextPanel = new JLabel("프로젝트");
		rankTextPanel.setBackground(Color.WHITE);
		rankTextPanel.setHorizontalAlignment(SwingConstants.CENTER);
		rankTextPanel.setFont(new Font("HY목각파임B", Font.BOLD, 40));
		rankTextPanel.setBounds(12, 10, 1161, 50);
		contentPane.add(rankTextPanel);

		JPanel projectsPanel = new JPanel();
		projectsPanel.setOpaque(false);
		projectsPanel.setBackground(new Color(0, true));
		projectsPanel.setLayout(new BoxLayout(projectsPanel, BoxLayout.Y_AXIS));

		pjs = new ProjectPanel[17];
		int lastCompletet = -1;
		int lastProceeding = -1;;
		
		for (int i = 0; i < pjs.length; i++) {
			ProjectPanel project = new ProjectPanel(mainFrame, ProjectDialog.this);
			project.getRankLabel().setText(String.valueOf((i + 1) + "주차"));
			project.getProjectName().setText(projectList.get(i).getProjectName());
			project.getRewardlbl().setText(String.valueOf(projectList.get(i).getRewardExp()));
			project.getTimeTakenlbl().setText(String.valueOf(projectList.get(i).getTime()));
			project.index = i;
			if (userProjectList.get(i).isComplete()) {
				lastCompletet = i;
				project.setComplete(true);
				project.getSelectablepnl().setVisible(false);
				project.getCompletepnl().setVisible(true);
			} else if (userProjectList.get(i).isProceeding()) {
				lastProceeding = i;
				project.getSelectablepnl().setVisible(false);
				project.setProceeding(true);
				project.getProceedingpnl().setVisible(true);
			}
			pjs[i] = project;
		}
		
		if ((lastProceeding == -1) && (lastCompletet != -1)) {
			pjs[lastCompletet + 1].getSelectablepnl().setVisible(false);
			pjs[lastCompletet + 1].setSelectable(true);
		} else if ((lastProceeding == -1) && (lastCompletet == -1)) {
			pjs[0].setSelectable(true);	
			pjs[0].getSelectablepnl().setVisible(false);			
		}
		
		for (int i = 0; i < pjs.length; i++) {
			if (!userProjectList.get(i).isComplete()) {
				projectsPanel.add(pjs[i]);
				projectsPanel.add(Box.createVerticalStrut(25));
			}
		}
		for (int i = 0; i < pjs.length; i++) {
			if (userProjectList.get(i).isComplete()) {
				projectsPanel.add(pjs[i]);
				projectsPanel.add(Box.createVerticalStrut(25));
			}
		}

		JScrollPane scrollPane = new JScrollPane(projectsPanel);
		scrollPane.setOpaque(false);
		scrollPane.setBackground(new Color(0, true));
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBounds(312, 72, 560, 619);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		contentPane.add(scrollPane);

		ClassLoader classLoader = getClass().getClassLoader();

		URL URLCloseButtonImage = classLoader.getResource("images/btn_img/closeBtnImage.png");
		ImageIcon CloseButtonIcon = new ImageIcon(URLCloseButtonImage);

		URL URLCloseButtonPushImage = classLoader.getResource("images/btn_img/closeBtnPushImage.png");
		ImageIcon CloseButtonPushIcon = new ImageIcon(URLCloseButtonPushImage);

		JButton closeButton = new JButton();
		closeButton.setBounds(488, 701, 200, 50);
		closeButton.setIcon(CloseButtonIcon);
		closeButton.setPressedIcon(CloseButtonPushIcon);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setTimeGo(true);
				dispose();
			}
		});
		contentPane.add(closeButton);
	}

	public void showGUI() {
		setVisible(true);
	}
	
	public ProjectPanel[] getPjs() {
		return pjs;
	}
}
