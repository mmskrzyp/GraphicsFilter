package graphic.filter.Algorithms;

import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public abstract class AbstractMatrixAlgorithm implements Algorithm {

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		double[][] factors = getFactors();
		for (int i = 1; i < inputImage.getWidth() - 1; i++) {
			for (int j = 1; j < inputImage.getHeight() - 1; j++) {
				inputImage
						.setRGB(i - 1,
								j - 1,
								(int) (inputImage.getRGB(i - 1, j - 1) * factors[0][0]));
				inputImage.setRGB(i, j - 1,
						(int) (inputImage.getRGB(i, j - 1) * factors[0][1]));
				inputImage
						.setRGB(i + 1,
								j - 1,
								(int) (inputImage.getRGB(i + 1, j - 1) * factors[0][2]));
				inputImage.setRGB(i - 1, j,
						(int) (inputImage.getRGB(i - 1, j) * factors[1][0]));
				inputImage.setRGB(i, j,
						(int) (inputImage.getRGB(i, j) * factors[1][1]));
				inputImage.setRGB(i + 1, j,
						(int) (inputImage.getRGB(i + 1, j) * factors[1][2]));
				inputImage
						.setRGB(i - 1,
								j + 1,
								(int) (inputImage.getRGB(i - 1, j + 1) * factors[2][0]));
				inputImage.setRGB(i, j + 1,
						(int) (inputImage.getRGB(i, j + 1) * factors[2][1]));
				inputImage
						.setRGB(i + 1,
								j + 1,
								(int) (inputImage.getRGB(i + 1, j + 1) * factors[2][2]));
			}
		}
		return inputImage;
	}

	public abstract double[][] getFactors();

}
