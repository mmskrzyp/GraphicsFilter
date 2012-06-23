package graphic.filter.Algorithms;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * 
 * @author mmskrzyp
 * 
 */
public abstract class Algorithm {

	public abstract BufferedImage execute(BufferedImage inputImage);

	public abstract String getName();

	public BufferedImage copyImage(BufferedImage bufferedImage) {
		ColorModel cm = bufferedImage.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bufferedImage.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

}
