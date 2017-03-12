package faceoff.competition;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

import faceoff.gui.GUI;

public class Competition {

	private CompetitionQueue queue;
	private Competitor left, right;
	private GUI gui;
	private boolean debug = true;
	
	public Competition() throws IOException {
		queue = new CompetitionQueue();
		gui = new GUI(this);
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
	
	public void fight(){
		setLeft(queue.pop());
		setRight(queue.pop());
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
}
