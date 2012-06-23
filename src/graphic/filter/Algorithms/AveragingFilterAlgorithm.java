package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class AveragingFilterAlgorithm extends Algorithm {

	BufferedImage inImage;

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		inImage = copyImage(inputImage);
		for (int i = 1; i < inImage.getWidth() - 1; i++) {
			for (int j = 1; j < inImage.getHeight() - 1; j++) {
				inputImage.setRGB(i, j, getOutputRGB(i, j));
			}
		}
		return inputImage;
	}

	private int getOutputRGB(int x, int y) {
		int outputRed = 0, outputGreen = 0, outputBlue = 0;
		Color color;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != j) {
					color = new Color(inImage.getRGB(x + i - 1, y + j - 1));
					outputRed += color.getRed();
					outputGreen += color.getGreen();
					outputBlue += color.getBlue();
				}
			}
		}
		outputRed /= 8;
		outputGreen /= 8;
		outputBlue /= 8;
		return new Color(getValidColorValue(outputRed),
				getValidColorValue(outputGreen), getValidColorValue(outputBlue))
				.getRGB();
	}

	private int getValidColorValue(int colorValue) {
		if (colorValue < 0)
			return 0;
		else if (colorValue > 255)
			return 255;
		return colorValue;
	}

	@Override
	public String getName() {
		return "Averaging Filter (Blurring)";
	}

}
