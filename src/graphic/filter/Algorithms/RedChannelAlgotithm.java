package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class RedChannelAlgotithm extends Algorithm {

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		for (int i = 1; i < inputImage.getWidth() - 1; i++) {
			for (int j = 1; j < inputImage.getHeight() - 1; j++) {
				Color color = new Color(inputImage.getRGB(i, j));
				int red = color.getRed();
				color = new Color(red, red, red);
				inputImage.setRGB(i, j, color.getRGB());
			}
		}
		return inputImage;
	}

	@Override
	public String getName() {
		return "Red Channel";
	}

}
