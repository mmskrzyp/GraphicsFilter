package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class MaximizingFilterAlgorithm extends Algorithm {

	BufferedImage inImage;

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		inImage = copyImage(inputImage);
		for (int i = 1; i < inImage.getWidth() - 1; i++) {
			for (int j = 1; j < inImage.getHeight() - 1; j++) {
				inImage.setRGB(i, j, getOutputRGB(inputImage, i, j));
			}
		}
		return inImage;
	}

	private int getOutputRGB(BufferedImage inputImage, int x, int y) {
		int maxRed = 0, maxGreen = 0, maxBlue = 0;
		Color color;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				color = new Color(inputImage.getRGB(x + i - 1, y + j - 1));
				if (maxRed < color.getRed())
					maxRed = color.getRed();
				if (maxGreen < color.getGreen())
					maxGreen = color.getGreen();
				if (maxBlue < color.getBlue())
					maxBlue = color.getBlue();
			}
		}
		return new Color(maxRed, maxGreen, maxBlue).getRGB();
	}

	@Override
	public String getName() {
		return "Maximizing Filter";
	}

}
