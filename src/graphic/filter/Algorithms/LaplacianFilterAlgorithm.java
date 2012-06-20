package graphic.filter.Algorithms;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class LaplacianFilterAlgorithm extends AbstractMatrixAlgorithm {

	@Override
	public double[][] getFactors() {
		return new double[][] { { -1, 0, -1 }, { 0, 4, 0 }, { -1, 0, -1 } };
	}

	@Override
	public String getName() {
		return "Laplacian Filter (Sharpening)";
	}

}
