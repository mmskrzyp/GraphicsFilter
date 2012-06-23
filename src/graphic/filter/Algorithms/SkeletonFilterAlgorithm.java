package graphic.filter.Algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SkeletonFilterAlgorithm extends Algorithm {

	private final int[][] template1 = { { 2, 0, 2 }, { 2, 1, 2 }, { 1, 1, 1 } };
	private final int[][] template2 = { { 2, 2, 1 }, { 0, 1, 1 }, { 2, 2, 1 } };
	private final int[][] template3 = { { 1, 1, 1 }, { 2, 1, 2 }, { 2, 0, 2 } };
	private final int[][] template4 = { { 1, 2, 2 }, { 1, 1, 0 }, { 1, 2, 2 } };

	private final List<int[][]> templates = new ArrayList<>();

	private BufferedImage inImage;

	public SkeletonFilterAlgorithm() {
		templates.add(template1);
		templates.add(template2);
		templates.add(template3);
		templates.add(template4);
	}

	@Override
	public BufferedImage execute(BufferedImage inputImage) {
		BufferedImage tempImage = copyImage(inputImage);
		int licz = 0;
		do {
			inImage = copyImage(tempImage);
			for (int i = 1; i < inputImage.getWidth() - 1; i++) {
				for (int j = 1; j < inputImage.getHeight() - 1; j++) {
					tempImage.setRGB(i, j, getOutputRGB(i, j));
				}
			}
			System.out.println("cokolwiek");
			licz++;
		} while (!areImagesEqual(inImage, tempImage) && licz < 20);
		return inImage;
	}

	private boolean areImagesEqual(BufferedImage img1, BufferedImage img2) {
		for (int i = 1; i < img1.getWidth() - 1; i++) {
			for (int j = 1; j < img2.getHeight() - 1; j++) {
				if (img1.getRGB(i, j) != img2.getRGB(i, j))
					return false;
			}
		}
		return true;
	}

	private int getOutputRGB(int x, int y) {
		boolean spelniaRed[][] = { { true, true, true }, { true, true, true },
				{ true, true, true } };
		boolean spelniaGreen[][] = { { true, true, true },
				{ true, true, true }, { true, true, true } };
		boolean spelniaBlue[][] = { { true, true, true }, { true, true, true },
				{ true, true, true } };
		boolean czyR = false, czyG = false, czyB = false;
		Color color = new Color(inImage.getRGB(x, y));
		for (int[][] template : templates) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					color = new Color(inImage.getRGB(x + i - 1, y + j - 1));
					if (template[i][j] == 0) {
						if (color.getRed() != 0)
							spelniaRed[i][j] = false;
						if (color.getGreen() != 0)
							spelniaGreen[i][j] = false;
						if (color.getBlue() != 0)
							spelniaBlue[i][j] = false;
					} else if (template[i][j] == 1) {
						if (color.getRed() != 255)
							spelniaRed[i][j] = false;
						if (color.getGreen() != 255)
							spelniaGreen[i][j] = false;
						if (color.getBlue() != 255)
							spelniaBlue[i][j] = false;
					}
				}
			}
			if (getPartOfColor(spelniaRed) == 0 || color.getRed() == 0)
				czyR = true;
			if (getPartOfColor(spelniaGreen) == 0 || color.getGreen() == 0)
				czyG = true;
			if (getPartOfColor(spelniaBlue) == 0 || color.getBlue() == 0)
				czyB = true;
		}
		color = new Color(inImage.getRGB(x, y));
		int newRed = czyR ? 0 : getPartOfColor(spelniaRed);
		int newGreen = czyG ? 0 : getPartOfColor(spelniaGreen);
		int newBlue = czyB ? 0 : getPartOfColor(spelniaBlue);
		return new Color(newRed, newGreen, newBlue).getRGB();
	}

	private int getPartOfColor(boolean[][] spelniaArray) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (!spelniaArray[i][j])
					return 255;
			}
		}
		return 0;
	}

	@Override
	public String getName() {
		return "Skeleton Filter";
	}

}
