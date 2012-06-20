package graphic.filter.Algorithms;

import java.awt.image.BufferedImage;

/**
 * 
 * @author mmskrzyp
 * 
 */
public interface Algorithm {

	public BufferedImage execute(BufferedImage inputImage);

	public String getName();

}
