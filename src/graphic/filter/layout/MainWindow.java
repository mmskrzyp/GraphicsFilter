package graphic.filter.layout;

import graphic.filter.ImageProcessor;
import graphic.filter.Algorithms.Algorithm;
import graphic.filter.Algorithms.AveragingFilterAlgorithm;
import graphic.filter.Algorithms.BlueChannelAlgotithm;
import graphic.filter.Algorithms.GreenChannelAlgotithm;
import graphic.filter.Algorithms.LaplacianFilterAlgorithm;
import graphic.filter.Algorithms.RedChannelAlgotithm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class MainWindow extends JFrame {

	private final ImageProcessor imageProcessor = new ImageProcessor();

	private final Algorithm[] algotithms = { new RedChannelAlgotithm(),
			new GreenChannelAlgotithm(), new BlueChannelAlgotithm(),
			new AveragingFilterAlgorithm(), new LaplacianFilterAlgorithm() };

	private final JFileChooser fileChooser = new JFileChooser();

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenu mnHelp = new JMenu("Help");

	private final JComboBox<String> comboBox = new JComboBox<String>();

	private BufferedImage inputImage = null;
	private final ImagePanel inputImagePanel = new ImagePanel();
	private final ImagePanel outputImagePanel = new ImagePanel();

	public MainWindow() {
		setTitle("Graphic Filter");
		setSize(628, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setJMenuBar(menuBar);
		JMenuItem jmiOpen = new JMenuItem("Open image...");
		jmiOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int returnVal = fileChooser.showOpenDialog(MainWindow.this);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						inputImage = ImageIO.read(file);
					} catch (IOException e) {
						e.printStackTrace();
					}
					inputImagePanel.drawImage(inputImage);
					System.out.println("Opening: " + file.getName() + "\n");
				} else {
					System.out.println("Open command cancelled by user.\n");
				}
			}
		});
		mnFile.add(jmiOpen);
		JMenuItem jmiSave = new JMenuItem("Save image...");
		jmiSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Zapis Obrazka wynikowego do pliku
			}
		});
		mnFile.add(jmiSave);
		mnFile.add(new JSeparator());
		mnFile.add(new JMenuItem("Exit"));
		menuBar.add(mnFile);
		menuBar.add(mnHelp);
		getContentPane().setLayout(null);

		// Przyciski
		JLabel lblChooseAFilter = new JLabel("Choose filter to use:");
		lblChooseAFilter.setBounds(10, 14, 145, 14);
		getContentPane().add(lblChooseAFilter);

		comboBox.setBounds(179, 11, 256, 20);
		populateAlgorithmsComboBox();
		getContentPane().add(comboBox);

		JButton btnApplyFilter = new JButton("Apply filter");
		btnApplyFilter.setBounds(495, 10, 107, 23);
		btnApplyFilter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (inputImage != null) {
					Algorithm algorithm = algotithms[comboBox
							.getSelectedIndex()];
					BufferedImage outImage = imageProcessor.processImage(
							algorithm, inputImage);
					outputImagePanel.drawImage(outImage);
				}
			}
		});
		getContentPane().add(btnApplyFilter);

		// Obrazki
		inputImagePanel.setBounds(10, 40, 291, 291);
		getContentPane().add(inputImagePanel);
		inputImagePanel.setLayout(null);
		inputImagePanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Input Image",
				TitledBorder.CENTER, TitledBorder.BOTTOM, null, null));

		outputImagePanel.setBounds(311, 40, 291, 291);
		getContentPane().add(outputImagePanel);
		outputImagePanel.setLayout(null);
		outputImagePanel.setBorder(new TitledBorder(null, "Output Image",
				TitledBorder.CENTER, TitledBorder.BOTTOM, null, null));
	}

	public void populateAlgorithmsComboBox() {
		for (Algorithm algorithm : algotithms) {
			comboBox.addItem(algorithm.getName());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainWindow window = new MainWindow();
				window.setVisible(true);
			}
		});
	}
}
