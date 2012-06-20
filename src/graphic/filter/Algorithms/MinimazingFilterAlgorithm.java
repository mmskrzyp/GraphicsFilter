package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MinimazingFilterAlgorithm implements Algorithm {

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
		int minRed = 255, minGreen = 255, minBlue = 255;
		Color color;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				color = new Color(inImage.getRGB(x + i - 1, y + j - 1));
				if (minRed > color.getRed())
					minRed = color.getRed();
				if (minGreen > color.getGreen())
					minGreen = color.getGreen();
				if (minBlue > color.getBlue())
					minBlue = color.getBlue();
			}
		}
		return new Color(minRed, minGreen, minBlue).getRGB();
	}

	@Override
	public String getName() {
		return "Minimizing Filter";
	}

}
