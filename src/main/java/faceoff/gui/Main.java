package faceoff.gui;

import java.io.File;
import java.io.FilenameFilter;
import java.util.LinkedList;
import java.util.List;

public class Main {


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
}
