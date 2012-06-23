package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class MultiplyImagesAlgorithm extends Algorithm {

	BufferedImage inImage;

	public BufferedImage execute(BufferedImage inputImage,
			BufferedImage inputImage2) {
		inImage = copyImage(inputImage);
		Color color;
		Color color2;
		for (int i = 1; i < inImage.getWidth() - 1; i++) {
			for (int j = 1; j < inImage.getHeight() - 1; j++) {
				color = new Color(inputImage.getRGB(i, j));
				color2 = new Color(inputImage2.getRGB(i, j));
				int newRed = color.getRed() * color2.getRed();
				int newGreen = color.getGreen() * color2.getGreen();
				int newBlue = color.getBlue() * color2.getBlue();
				Color newColor = new Color(getValidColorValue(newRed),
						getValidColorValue(newGreen),
						getValidColorValue(newBlue));
				inImage.setRGB(i, j, newColor.getRGB());
			}
		}
		return inImage;
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
		return "Multiply images";
	}

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		// TODO Auto-generated method stub
		return null;
	}

}
