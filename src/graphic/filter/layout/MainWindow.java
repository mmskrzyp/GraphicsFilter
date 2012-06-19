package graphic.filter.layout;

import graphic.filter.ImageProcessor;

import java.awt.Canvas;
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

	private BufferedImage inputImage = null;
	private final Canvas canvas = new Canvas();

	public MainWindow() {
		setTitle("Graphic Filter");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setJMenuBar(menuBar);
		menuBar.add(mnFile);
		menuBar.add(mnHelp);

		JButton btnNewButton = new JButton("Open image...");
		btnNewButton.setBounds(10, 11, 126, 23);
		btnNewButton.addActionListener(new ActionListener() {
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
					canvas.prepareImage(inputImage, MainWindow.this);
					System.out.println("Opening: " + file.getName() + "\n");
				} else {
					System.out.println("Open command cancelled by user.\n");
				}
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 285, 285);
		getContentPane().add(scrollPane);

		scrollPane.setViewportView(canvas);

		try {
			inputImage = ImageIO.read(new File("D:\\Pictures\\misiek.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		canvas.prepareImage(inputImage, inputImage.getWidth(),
				inputImage.getHeight(), MainWindow.this);
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
