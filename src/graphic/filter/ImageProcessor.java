package graphic.filter;

import graphic.filter.Algorithms.Algorithm;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class ImageProcessor {

	public BufferedImage processImage(Algorithm algorithm,
			BufferedImage inputImage) {

		BufferedImage inImage = copyImage(inputImage);
		BufferedImage outImage = algorithm.execute(inImage);

		return outImage;
	}

	private BufferedImage copyImage(BufferedImage bufferedImage) {
		ColorModel cm = bufferedImage.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bufferedImage.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
