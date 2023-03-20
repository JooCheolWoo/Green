package main.store;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import main.MainFrame;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class SkillPanel extends JPanel implements MouseListener{

	private JLabel imageLabel;
	private JLabel SkillNameLabel;
	private JLabel SkillDescriptionLabel;
	private JLabel levelLabel;
	private JLabel priceLabel;
	private int level = 0;
	private StoreEventImpl storeEventImpl;

	public SkillPanel(StoreEventImpl storeEventImpl) {
		
		this.storeEventImpl = storeEventImpl;
		EtchedBorder eborder = new EtchedBorder(EtchedBorder.RAISED);
		
		setBorder(eborder);
		setBackground(Color.LIGHT_GRAY);
		setBounds(100, 100, 560, 100);
		setLayout(null);
		
		imageLabel = new JLabel("");
		imageLabel.setBorder(eborder);
		imageLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setForeground(Color.BLACK);
		imageLabel.setBackground(Color.WHITE);
		imageLabel.setOpaque(true);
		imageLabel.setBounds(12, 10, 80, 80);
		add(imageLabel);
		
		SkillNameLabel = new JLabel("item name");
		SkillNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		SkillNameLabel.setBorder(eborder);
		SkillNameLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		SkillNameLabel.setForeground(Color.BLACK);
		SkillNameLabel.setBackground(Color.WHITE);
		SkillNameLabel.setOpaque(true);
		SkillNameLabel.setBounds(104, 10, 145, 23);
		add(SkillNameLabel);
		
		SkillDescriptionLabel = new JLabel("description");
		SkillDescriptionLabel.setBorder(eborder);
		SkillDescriptionLabel.setVerticalAlignment(SwingConstants.TOP);
		SkillDescriptionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		SkillDescriptionLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		SkillDescriptionLabel.setBackground(Color.WHITE);
		SkillDescriptionLabel.setOpaque(true);
		SkillDescriptionLabel.setBounds(104, 43, 352, 47);
		add(SkillDescriptionLabel);
		
		levelLabel = new JLabel("LV." + level);
		levelLabel.setBorder(eborder);
		levelLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		levelLabel.setBackground(Color.WHITE);
		levelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		levelLabel.setOpaque(true);
		levelLabel.setBounds(468, 10, 80, 80);
		add(levelLabel);
		
		priceLabel = new JLabel("skill price");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		priceLabel.setOpaque(true);
		priceLabel.setForeground(Color.BLACK);
		priceLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		priceLabel.setBackground(Color.WHITE);
		priceLabel.setBounds(296, 10, 160, 23);
		add(priceLabel);
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL URLCigaImage = classLoader.getResource("images/skill_img/ciga.png");
		ImageIcon cigaImageIcon = new ImageIcon(URLCigaImage);
		
		JLabel cigaImageLabel = new JLabel("");
		cigaImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cigaImageLabel.setOpaque(true);
		cigaImageLabel.setIcon(cigaImageIcon);
		cigaImageLabel.setForeground(Color.BLACK);
		cigaImageLabel.setFont(new Font("휴먼편지체", Font.BOLD, 15));
		cigaImageLabel.setBackground(Color.WHITE);
		cigaImageLabel.setBounds(261, 10, 23, 23);
		add(cigaImageLabel);
		
		addMouseListener(this);
	}
	
	public JLabel getImageLabel() {
		return imageLabel;
	}

	public JLabel getSkillNameLabel() {
		return SkillNameLabel;
	}

	public JLabel getSkillDescriptionLabel() {
		return SkillDescriptionLabel;
	}

	public JLabel getLevelLabel() {
		return levelLabel;
	}

	public JLabel getPriceLabel() {
		return priceLabel;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		storeEventImpl.payCiga(SkillPanel.this);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		setBackground(Color.pink);		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		setBackground(Color.LIGHT_GRAY);		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}
}
