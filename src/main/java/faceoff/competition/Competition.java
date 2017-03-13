package faceoff.competition;

import java.awt.EventQueue;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import com.sun.jna.platform.FileUtils;

import faceoff.elo.ELO;
import faceoff.gui.GUI;

public class Competition {

	private CompetitionQueue queue;
	private Competitor left, right;
	private List<File> toDelete;
	private File sourceDirectory, winnerDirectory, loserDirectory;
	private GUI gui;
	private boolean debug = true;
	
	public Competition() throws IOException {
		toDelete = new LinkedList<>();
		queue = new CompetitionQueue();
		gui = new GUI(this);
		startCompetition();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new Competition();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
		});
	}
	
	private void debug(String message){
		if (debug){
			System.out.println("[Competition] "+message);
		}
	}
	
	public void startCompetition(){
		queue.getQueue().clear();
		gui.setMainProgress(0);

		JFileChooser directoryChooser=new JFileChooser("D:/");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setDialogTitle("Select a directory containing your unsorted images");
		
		if (directoryChooser.showOpenDialog(gui.mainFrame) == JFileChooser.APPROVE_OPTION){
			sourceDirectory = directoryChooser.getSelectedFile();
			if (sourceDirectory == null || !sourceDirectory.isDirectory()){
				System.out.println("Not a directory.");
				cancel();
				return;
			}
		} else {
			cancel();
			return;
		}
		
		loadImages(sourceDirectory);
		
		directoryChooser=new JFileChooser("D:/Workspace/Winners");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setDialogTitle("Select a directory for your best images");
		
		if (directoryChooser.showOpenDialog(gui.mainFrame) == JFileChooser.APPROVE_OPTION){
			winnerDirectory = directoryChooser.getSelectedFile();
			if (winnerDirectory == null || !winnerDirectory.isDirectory()){
				System.out.println("Not a directory.");
				cancel();
				return;
			}
		} else {
			cancel();
			return;
		}
		
		directoryChooser=new JFileChooser("D:/Workspace/Losers");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setDialogTitle("Select a directory for your worst images");
		
		if (directoryChooser.showOpenDialog(gui.mainFrame) == JFileChooser.APPROVE_OPTION){
			loserDirectory = directoryChooser.getSelectedFile();
			if (loserDirectory == null || !loserDirectory.isDirectory()){
				System.out.println("Not a directory.");
				cancel();
				return;
			}
		} else {
			cancel();
			return;
		}
		
		fight();
	}
	
	protected void loadImages(File directory){
		// array of supported extensions (use a List if you prefer)
		final String[] EXTENSIONS = new String[] { "jpg", "png", "bmp", "jpeg" };

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

		if (directory.isDirectory()) { // make sure it's a directory
            for (final File image : directory.listFiles(IMAGE_FILTER)) {
            	queue.push(new Competitor(image));
            }
            queue.shuffle();
		} else {
			debug("Not a directory");
			cancel();
		}
		
		gui.setMainProgressMaximum(queue.size());
	}
	
	public void fight(){
		left = queue.pop();
		right = queue.pop();

		try {
			gui.battle(left, right);
		} catch (IOException e) {
			cancel();
			return;
		}
		gui.setPause(false);
	}
	
	public void skip(){
		queue.insert_shuffle(left); left = null;
		queue.insert_shuffle(right); right = null;
		fight();
	}
	
	public void leftWins(){
		ELO elo = new ELO(left, right);
		elo.championWins();
		left.setFought();
		right.setFought();
		gui.incrementMainProgress();
		skip();
	}
	
	public void rightWins(){
		ELO elo = new ELO(left, right);
		elo.challengerWins();
		left.setFought();
		right.setFought();
		gui.incrementMainProgress();
		skip();
	}
	
	public void bothWin(){
		ELO elo = new ELO(left, right);
		elo.bothWin();
		left.setFought();
		right.setFought();
		gui.incrementMainProgress();
		skip();
	}
	
	public void bothLose(){
		ELO elo = new ELO(left, right);
		elo.bothLose();
		left.setFought();
		right.setFought();
		gui.incrementMainProgress();
		skip();
	}
	
	public void deleteLeft(){
		toDelete.add(left.getImage());
		left = queue.pop();
		try {
			gui.battle(left, right);
		} catch (IOException e) {
			cancel();
			return;
		}
		gui.setMainProgressMaximum(queue.size());
	}
	
	public void deleteRight(){
		toDelete.add(right.getImage());
		right = queue.pop();
		try {
			gui.battle(left, right);
		} catch (IOException e) {
			cancel();
			return;
		}
		gui.setMainProgressMaximum(queue.size());
	}
	
	public void commit(){
		gui.setPause(true);
		queue.push(left);
		queue.push(right);
		int howMany = queue.size() / 3;
		List<Competitor> best = queue.getBest(howMany);
		List<Competitor> worst = queue.getWorst(howMany);

		String winnerDirectoryName = winnerDirectory.getAbsolutePath()+File.separator;
		for (Competitor competitor : best) {
			if (competitor.getHasFought()){
				File image = competitor.getImage();
				String filename = image.getName();
				image.renameTo(new File(winnerDirectoryName + filename));
			}
		}

		String loserDirectoryName = loserDirectory.getAbsolutePath()+File.separator;
		for (Competitor competitor : worst) {
			if (competitor.getHasFought()){
				File image = competitor.getImage();
				String filename = image.getName();
				image.renameTo(new File(loserDirectoryName + filename));
			}
		}
		
		try {
			if (!toDelete.isEmpty()){
				FileUtils fileUtils = FileUtils.getInstance();
				File[] filesToDelete = toDelete.toArray(new File[toDelete.size()]);
				fileUtils.moveToTrash(filesToDelete);
			}
		} catch (IOException iOE){
			debug("There was a problem deleting all those files, yo.");
		}
		
		cancel();
	}
	
	public void cancel(){
		gui.mainFrame.setVisible(false); //you can't see me!
		gui.mainFrame.dispose(); //Destroy the JFrame object
	}
}
