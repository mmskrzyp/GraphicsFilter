package graphic.filter.Algorithms;

import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class AveragingFilterAlgorithm implements Algorithm {

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		for (int i = 1; i < inputImage.getWidth() - 1; i++) {
			for (int j = 1; j < inputImage.getHeight() - 1; j++) {

				int averageRGB = (inputImage.getRGB(i - 1, j - 1)
						+ inputImage.getRGB(i, j - 1)
						+ inputImage.getRGB(i + 1, j - 1)
						+ inputImage.getRGB(i - 1, j)
						+ inputImage.getRGB(i + 1, j)
						+ inputImage.getRGB(i - 1, j + 1)
						+ inputImage.getRGB(i, j + 1) + inputImage.getRGB(
						i + 1, j + 1)) / 8;
				inputImage.setRGB(i, j, averageRGB);
			}
		}
		return inputImage;
	}

	@Override
	public String getName() {
		return "Averaging Filter";
	}

}
