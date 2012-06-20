package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class MaximizingFilterAlgorithm implements Algorithm {

	BufferedImage inImage;

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		inImage = inputImage;
		for (int i = 1; i < inImage.getWidth() - 1; i++) {
			for (int j = 1; j < inImage.getHeight() - 1; j++) {
				inputImage.setRGB(i, j, getOutputRGB(i, j));
			}
		}
		return inImage;
	}

	private int getOutputRGB(int x, int y) {
		int maxRed = 0, maxGreen = 0, maxBlue = 0;
		Color color;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				color = new Color(inImage.getRGB(x + i - 1, y + j - 1));
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
