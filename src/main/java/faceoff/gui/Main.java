package faceoff.gui;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
}
