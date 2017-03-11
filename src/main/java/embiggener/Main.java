package embiggener;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

	public void embiggen_file(File toEmbiggen, File destinationFile) throws IOException {

		String charset = "utf-8";
		String requestURL = "http://waifu2x.udp.jp/api";
		MultipartUtility multipart = new MultipartUtility(requestURL, charset);

		multipart.addFormField("style", "art");
		multipart.addFormField("noise", "1");
		multipart.addFormField("scale", "2");

		multipart.addFilePart("file", toEmbiggen);
		multipart.finish(destinationFile);
	}

	public List<File> get_images(File directory) {
		// array of supported extensions (use a List if you prefer)
		final String[] EXTENSIONS = new String[] { "jpg", "png", "bmp" };

		// filter to identify images based on their extensions
		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				for (final String ext : EXTENSIONS) {
					if (name.endsWith("." + ext)) {
						return true;
					}
				}
				return false;
			}
		};
		
		List<File> images = new LinkedList<File>();

		if (directory.isDirectory()) { // make sure it's a directory
            for (final File image : directory.listFiles(IMAGE_FILTER)) {
            	images.add(image);
            }
		}
		
		return images;
	}

	public Main() {
		JFrame main_window = new JFrame("Face-off");
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main_window.setExtendedState(main_window.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		BorderLayout main_layout = new BorderLayout();
		main_window.setLayout(main_layout);

		JLabel left_label = new JLabel("Image List", JLabel.CENTER);
		main_window.add(left_label, BorderLayout.WEST);

		JLabel right_label = new JLabel("Results", JLabel.CENTER);
		main_window.add(right_label, BorderLayout.EAST);

		JLabel middle_label = new JLabel("Middle", JLabel.LEFT);
		main_window.add(middle_label, BorderLayout.CENTER);

		main_window.setVisible(true);

	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new Main();
	}
}
