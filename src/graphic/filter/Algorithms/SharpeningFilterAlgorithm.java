package graphic.filter.Algorithms;

public class SharpeningFilterAlgorithm extends AbstractMatrixAlgorithm {

	@Override
	public double[][] getFactors() {
		return new double[][] { { -1, 0, -1 }, { 0, 5, 0 }, { -1, 0, -1 } };
	}

	@Override
	public String getName() {
		return "Sharpening Filter";
	}

}