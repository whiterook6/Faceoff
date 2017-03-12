package faceoff.competition;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class Competition {
	private CompetitionQueue queue;

	public Competition() {
		queue = new CompetitionQueue();
	}

	public int addDirectory(Path directory) throws IOException {
		int beforeCount = queue.size();
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attr) {
				final String[] EXTENSIONS = new String[]{".png", ".jpg", ".jpeg", ".bmp"};
				if (attr.isRegularFile()){
					String filename = file.toString();
					for (String extension : EXTENSIONS) {
						if (filename.endsWith(extension)){
							queue.push(new Competitor(file.toFile()));
						}
					}
				}
				
				return FileVisitResult.CONTINUE;
			}
		});
		return queue.size() - beforeCount;
	}

	public void printContents(){
		for (Competitor competitor : queue.getQueue()) {
			System.out.println(competitor.getImage().getAbsolutePath());
		}
	}
}
