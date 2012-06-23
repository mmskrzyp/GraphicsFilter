package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class BlueChannelAlgotithm extends Algorithm {

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		for (int i = 1; i < inputImage.getWidth() - 1; i++) {
			for (int j = 1; j < inputImage.getHeight() - 1; j++) {
				Color color = new Color(inputImage.getRGB(i, j));
				int blue = color.getBlue();
				color = new Color(blue, blue, blue);
				inputImage.setRGB(i, j, color.getRGB());
			}
		}
		return inputImage;
	}

	@Override
	public String getName() {
		return "Blue Channel";
	}

}
