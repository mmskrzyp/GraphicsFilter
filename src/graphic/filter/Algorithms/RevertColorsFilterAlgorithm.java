package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class RevertColorsFilterAlgorithm extends Algorithm {

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		for (int i = 1; i < inputImage.getWidth() - 1; i++) {
			for (int j = 1; j < inputImage.getHeight() - 1; j++) {
				Color color = new Color(inputImage.getRGB(i, j));
				int newRed = 255 - color.getRed();
				int newGreen = 255 - color.getGreen();
				int newBlue = 255 - color.getBlue();
				color = new Color(newRed, newGreen, newBlue);
				inputImage.setRGB(i, j, color.getRGB());
			}
		}
		return inputImage;
	}

	@Override
	public String getName() {
		return "Revert Colors";
	}

}
