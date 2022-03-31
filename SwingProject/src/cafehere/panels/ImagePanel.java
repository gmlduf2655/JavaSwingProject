package cafehere.panels;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private ImageIcon backgroundImageicon = new ImageIcon("images/cafe_background.jpg");
	private Image backgroundImage = backgroundImageicon.getImage();
	public ImagePanel() {
		setSize(900, 600);
	}
	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(backgroundImage, 0, 0, null);
		setOpaque(false);
	}
	
}
