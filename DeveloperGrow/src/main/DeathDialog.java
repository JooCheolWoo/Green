package main;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import database.controllDB.UpdateDB;
import guiDesign.ImagePanel;
import guiDesign.Methods;
import main.MainFrame;

public class DeathDialog extends JDialog {
	private MainFrame mainFrame;
	private ImagePanel contentPane;
	
	public DeathDialog(int x, int y, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		mainFrame.setTimeGo(false);
		mainFrame.setProjectGo(false);
		mainFrame.stopSound();
		mainFrame.getUserInfo().setGameover(true);
		mainFrame.getGameControllerImpl().saveRanking();
		UpdateDB.updateUserInfo(mainFrame.getUserInfo());
		
		getContentPane().setLayout(null);
		setUndecorated(true);
		setModal(true);
		setBounds(x, y, 1200, 800);
		setBackground(new Color(0, 0, 0, 100));
		
		contentPane = new ImagePanel(Methods.converImage(getClass(), "images/back_img/DeathBackground.png"));
		contentPane.setBounds(0, 0, 1200, 800);
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		
		JButton returnBtn = new JButton("다시하기");
		returnBtn.setBounds(1030, 698, 146, 53);
		returnBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (mainFrame.getPb().getTimeHp() != null) {
					mainFrame.getPb().getTimeHp().cancel();
				}
				if (mainFrame.getPb().getTimeStress() != null) {
					mainFrame.getPb().getTimeStress().cancel();
				}
				if (mainFrame.getPb().getTimeHealth() != null) {
					mainFrame.getPb().getTimeHealth().cancel();
				}
				if (mainFrame.getProjectEventImpl().getProjectTimer() != null) {
					mainFrame.getProjectEventImpl().getProjectTimer().cancel();
				}
				if (mainFrame.getGameControllerImpl().getCurrentTime() != null) {
					mainFrame.getGameControllerImpl().getCurrentTime().cancel();
				}
				int userId = mainFrame.getUserId();
				mainFrame.dispose();
				MainFrame mainFrameNew = new MainFrame(userId);
				dispose();
				mainFrameNew.showGUI();
			}
		});
		
		JButton finishBtn = new JButton("그만하기");
		finishBtn.setBounds(27, 698, 146, 53);
		finishBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		contentPane.add(finishBtn);
		contentPane.add(returnBtn);
	}
	
	public void showGUI() {
		setVisible(true);
	}
}
