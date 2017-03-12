package faceoff.competition;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import faceoff.gui.GUI;

public class Competition {

	private CompetitionQueue queue;
	private Competitor left, right;
	private File sourceDirectory, winnerDirectory, loserDirectory;
	private GUI gui;
	private boolean debug = true;
	
	public Competition() throws IOException {
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
		JFileChooser directoryChooser=new JFileChooser("D:/");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setDialogTitle("Select a directory containing your unsorted images");
		
		if (directoryChooser.showOpenDialog(gui.mainFrame) == JFileChooser.APPROVE_OPTION){
			sourceDirectory = directoryChooser.getSelectedFile();
		} else {
			cancel();
		}
		
		directoryChooser=new JFileChooser("D:/");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setDialogTitle("Select a directory for your best images");
		
		if (directoryChooser.showOpenDialog(gui.mainFrame) == JFileChooser.APPROVE_OPTION){
			winnerDirectory = directoryChooser.getSelectedFile();
		} else {
			cancel();
		}
		
		directoryChooser=new JFileChooser("D:/");
		directoryChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		directoryChooser.setDialogTitle("Select a directory for your worst images");
		
		if (directoryChooser.showOpenDialog(gui.mainFrame) == JFileChooser.APPROVE_OPTION){
			loserDirectory = directoryChooser.getSelectedFile();
		} else {
			cancel();
		}
		
		gui.setPause(true);
	}
	
	public void fight(){
		setLeft(queue.pop());
		setRight(queue.pop());
		gui.setPause(false);
	}
	
	public void setLeft(Competitor competitor){
		left = competitor;
		gui.setLeft(left);
	}
	
	public void setRight(Competitor competitor){
		right = competitor;
		gui.setRight(right);
	}
	
	public void skip(){
//		queue.insert_shuffle(left); left = null;
//		queue.insert_shuffle(right); right = null;
//		fight();
		debug("Skip");
	}
	
	public void leftWins(){
//		ELO elo = new ELO(left, right);
//		elo.championWins();
		debug("LeftWins");
		skip();
	}
	
	public void rightWins(){
//		ELO elo = new ELO(left, right);
//		elo.challengerWins();
		debug("RightWins");
		skip();
	}
	
	public void bothWin(){
//		ELO elo = new ELO(left, right);
//		elo.bothWin();
		debug("BothWin");
		skip();
	}
	
	public void bothLose(){
//		ELO elo = new ELO(left, right);
//		elo.bothLose();
		debug("BothLose");
		skip();
	}
	
	public void deleteLeft(){
//		left.getImage().delete();
//		set_left(queue.pop());
		debug("deleteLeft");
	}
	
	public void deleteRight(){
//		right.getImage().delete();
//		set_right(queue.pop());
		debug("deleteRight");
	}
	
	public void commit(){
		
	}
	
	public void cancel(){
		gui.mainFrame.setVisible(false); //you can't see me!
		gui.mainFrame.dispose(); //Destroy the JFrame object
	}
}
