package main.minigame.different;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import guiDesign.Methods;
import main.MainFrame;
import main.minigame.GameDialog;

public class ToryMain extends JFrame {

	final Tory left = new Tory("images/dif_img/1.jpg");
	final Tory right = new Tory("images/dif_img/2.png");
	JLabel click;
	
	
	// 틀린그림부분의 좌표값 배열
	static int[] imageX = { 150, 278, 205, 205, 310 };
	static int[] imageY = { 242, 242, 290, 333, 160 };
	// 틀린부분의 범위 기본 설정값
	static final int range = 30;
	private MainFrame mainFrame;
	private GameDialog gameDialog;
	
	public ToryMain(String string, MainFrame mainFrame, GameDialog gameDialog) {
		super(string);
		this.mainFrame = mainFrame;
		this.gameDialog = gameDialog;
		CustomMouseAdapter cma = new CustomMouseAdapter();
		left.setBounds(10, 10, 470, 541);
		// imga 이미지가 저장된 JPanel a 객체에 감지기 설정
		left.addMouseListener(cma);
		right.setBounds(492, 10, 458, 620);
		// imab 이미지가 저장된 JPanel b 객체에 감지기 설정
		right.addMouseListener(cma);
		getContentPane().setLayout(null);

		// 프레임에 판넬a,b부착, 각각 위치설정
		getContentPane().add(left);

		left.setLayout(null);

		click = new JLabel();
		click.setIcon(Methods.convertToIcon(getClass(), "images/dif_img/check.png"));
		click.setSize(70, 70);
		right.add(click);
		
		JLabel leftimg = new JLabel("");
		leftimg.setBounds(6, 23, 458, 591);
		leftimg.setIcon(Methods.convertToIcon(getClass(), "images/dif_img/1.jpg"));
		left.add(leftimg);
		
		JLabel lblNewLabel = new JLabel("틀린 토리 찾기");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 31));
		lblNewLabel.setBounds(92, 0, 372, 81);
		left.add(lblNewLabel);
		getContentPane().add(right);
		right.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(Methods.convertToIcon(getClass(), "images/btn_img/minigameCloseBtn.png"));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setPressedIcon(Methods.convertToIcon(getClass(), "images/btn_img/minigameClosePushBtn.png"));
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(new Color(255, 0, 0, 0));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainFrame.setTimeGo(true);
				dispose();
			}
		});
		btnNewButton.setBounds(358, 10, 100, 45);
		right.add(btnNewButton);
		getContentPane().add(right);
		right.setLayout(null);
		
		JLabel rightimg = new JLabel("");
		rightimg.setBounds(0, 10, 458, 620);
		rightimg.setIcon(Methods.convertToIcon(getClass(), "images/dif_img/2.png"));
		right.add(rightimg);
		
		JLabel lblNewLabel_1 = new JLabel("힌트 5군데");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 31));
		lblNewLabel_1.setBounds(0, 0, 337, 70);
		right.add(lblNewLabel_1);

//		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(200, 100, 1000, 600);
		setResizable(false);
//		setVisible(true);
	}
	
	public void showGUI() {
		setVisible(true);
	}

	public boolean isRangeof(int index, Point p) {
		int pointX = p.x;
		
		int pointY = p.y;
		
		return (pointX >= imageX[index] - range) && (pointX <= imageX[index] + range)
				&& (pointY >= imageY[index] - range) && (pointY <= imageY[index] + range);
	}

	class CustomMouseAdapter extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			
			click.setLocation(x - 20, y - 20);
		}

		public void mouseReleased(MouseEvent e) {
			Point p = e.getPoint();
			if (isRangeof(0, p)) {
				Tory.f1 = true; //
				left.repaint();
				right.repaint();

			} else if (isRangeof(1, p)) {
				Tory.f2 = true;
				left.repaint();
				right.repaint();

			} else if (isRangeof(2, p)) {
				Tory.f3 = true;
				left.repaint();
				right.repaint();

			} else if (isRangeof(3, p)) {
				Tory.f4 = true;
				left.repaint();
				right.repaint();

			} else if (isRangeof(4, p)) {
				Tory.f5 = true;
				left.repaint();
				right.repaint();

			} else if (p.x >= 40 && p.x <= 100 && p.y >= 450 && p.y <= 500) {
				JOptionPane.showMessageDialog(null, "어따 클릭하냐ㅋ");
				left.repaint();
				right.repaint();

			} else if (p.x >= 250 && p.x <= 500 && p.y >= 400 && p.y <= 550) {
				JOptionPane.showMessageDialog(null, "틀맀따 갈기지마라");
				left.repaint();
				right.repaint();
			} 

			if (Tory.f1 == true && Tory.f2 == true && Tory.f3 == true && Tory.f4 == true && Tory.f5 == true) {
				JOptionPane.showMessageDialog(null, "축하합니다.");
				Tory.f1 = false;
				Tory.f2 = false;
				Tory.f3 = false;
				Tory.f4 = false;
				Tory.f5 = false;
				left.repaint();
				right.repaint();
				mainFrame.getUserInfo().setCiga(mainFrame.getUserInfo().getCiga() + 10);
				mainFrame.getNumOfcigalbl().setText(String.valueOf(mainFrame.getUserInfo().getCiga()));
				mainFrame.getProjectEventImpl().expProgressBar(20);
				mainFrame.revalidate();
				mainFrame.repaint();
				mainFrame.getUserInfo().setTory(true);
				mainFrame.setTimeGo(true);
				mainFrame.setProjectGo(true);
				dispose();
			}
		}
		
	}

	
	
//	public static void main(String[] args) {
//		new ToryMain("틀린그림찾기");
//	}// main
}// end