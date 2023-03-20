package main.minigame.different;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import guiDesign.Methods;

public class Hidee extends JPanel {

	private JLabel jlb1, jlb2;
	private ImageIcon img, img2;

	// 틀린그림
	static boolean f1 = false;
	static boolean f2 = false;
	static boolean f3 = false;
	static boolean f4 = false;
	static boolean f5 = false;
	static boolean f6 = false;
	static boolean f7 = false;

	/**
	 * 이미지파일을 매개변수로 받는다
	 * 
	 * @param String imageFile
	 */
	public Hidee(String imageFile) {
		// ThigauEEx()main에서 이미지 파일을 전달받아 이미지아이콘객체생성

		// 이미지아이콘객체를 라벨에 넣고 객체생성
		jlb1 = new JLabel(Methods.convertToIcon(getClass(), imageFile));
		// 상속받은 JPanel에 생성한 라벨객체를 저장
		add(jlb1);
	}// Grim()

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		// 동그라미 색깔 빨강으로 표시
		g.setColor(Color.RED);
		// 틀린그림부분에 동그라미 그리기
		if (f1 == true) {
			g.drawOval(240, 408, 50, 50); // brush
		}
		if (f2 == true) {
			g.drawOval(500, 350, 50, 50); // cherry
		}
		if (f3 == true) {
			g.drawOval(368, 143, 50, 50); // bommerang
		}
		if (f4 == true) {
			g.drawOval(568, 340, 50, 50); // rabbit
		}
		if (f5 == true) {
			g.drawOval(430, 405, 50, 50); // teepee
		}
		if (f6 == true) {
			g.drawOval(220, 350, 50, 50); // hockey stick
		}
		if (f7 == true) {
			g.drawOval(580, 520, 50, 50); // broom
		}
		
	}// paint()
}// end