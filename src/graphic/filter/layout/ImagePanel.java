package graphic.filter.layout;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class ImagePanel extends JPanel {

	private BufferedImage image;

	public void drawImage(BufferedImage image) {
		this.image = image;
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}

}
