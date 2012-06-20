package graphic.filter.layout;

import graphic.filter.ImageProcessor;

import java.awt.Choice;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * 
 * @author mmskrzyp
 * 
 */
public class MainWindow extends JFrame {

	private final ImageProcessor imageProcessor = new ImageProcessor();

	private final JFileChooser fileChooser = new JFileChooser();

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenu mnHelp = new JMenu("Help");

	private final JButton btnOpenImage = new JButton("Open image...");
	private final JButton btnSaveImage = new JButton("Save image...");

	private BufferedImage inputImage = null;
	private final ImagePanel inputImagePanel;
	private final ImagePanel outputImagePanel = null;

	public MainWindow() {
		setTitle("Graphic Filter");
		setSize(628, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setJMenuBar(menuBar);
		menuBar.add(mnFile);
		menuBar.add(mnHelp);

		inputImagePanel = new ImagePanel();
		inputImagePanel.setBounds(35, 61, 126, 86);

		JScrollPane scrollPane = new JScrollPane(inputImagePanel);
		scrollPane.setBounds(10, 40, 291, 291);
		getContentPane().add(scrollPane);

		btnOpenImage.setBounds(10, 11, 110, 23);
		btnOpenImage.addActionListener(new ActionListener() {
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
		getContentPane().setLayout(null);
		getContentPane().add(btnOpenImage);

		JScrollPane scrollPane_1 = new JScrollPane(outputImagePanel);
		scrollPane_1.setBounds(311, 39, 291, 291);
		getContentPane().add(scrollPane_1);

		getContentPane().add(btnSaveImage);

		Choice choice = new Choice();
		choice.setBounds(165, 11, 283, 20);
		getContentPane().add(choice);

		btnSaveImage.setBounds(492, 11, 110, 23);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (inputImage != null) {
			g.drawImage(inputImage, inputImage.getWidth(), getHeight(), this);
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
