package main.minigame.different;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import guiDesign.Methods;

public class Tory extends JPanel {

	private JLabel jlb1, jlb2;
	private ImageIcon img, img2;

	// 틀린그림
	static boolean f1 = false;
	static boolean f2 = false;
	static boolean f3 = false;
	static boolean f4 = false;
	static boolean f5 = false;

	public Tory(String imageFile) {
		// ThigauEEx()main에서 이미지 파일을 전달받아 이미지아이콘객체생성
		// 이미지아이콘객체를 라벨에 넣고 객체생성
		jlb1 = new JLabel(Methods.convertToIcon(getClass(), imageFile));
		// 상속받은 JPanel에 생성한 라벨객체를 저장
		add(jlb1);
	}// Grim()

//	public void showGUI() {
//		setVisible(true);
//	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// 동그라미 색깔 빨강으로 표시
		g.setColor(Color.RED);
		// 틀린그림부분에 동그라미 그리기
		if (f1 == true) {
			g.drawOval(150, 242, 50, 50); // 왼쪽
		}
		if (f2 == true) {
			g.drawOval(278, 242, 50, 50); // 오른쪽
		}
		if (f3 == true) {
			g.drawOval(205, 290, 50, 50); // 코
		}
		if (f4 == true) {
			g.drawOval(205, 333, 50, 50); // 입
		}
		if (f5 == true) {
			g.drawOval(310, 160, 50, 50); // 귀
		}
	}// paint()
}// end