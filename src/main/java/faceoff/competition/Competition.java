package faceoff.competition;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

import faceoff.gui.GUI;

public class Competition {

	private CompetitionQueue queue;
	private Competitor left, right;
	private GUI gui;
	
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
	
	public void fight(){
		set_left(queue.pop());
		set_right(queue.pop());
	}
	
	public void set_left(Competitor competitor){
		left = competitor;
		gui.set_left(left);
	}
	
	public void set_right(Competitor competitor){
		right = competitor;
		gui.set_right(right);
	}
	
	public void skip(){
//		queue.insert_shuffle(left); left = null;
//		queue.insert_shuffle(right); right = null;
//		fight();
		System.out.println("Skip");
	}
	
	public void leftWins(){
//		ELO elo = new ELO(left, right);
//		elo.championWins();
		System.out.println("LeftWins");
		skip();
	}
	
	public void right_wins(){
//		ELO elo = new ELO(left, right);
//		elo.challengerWins();
		System.out.println("RightWins");
		skip();
	}
	
	public void both_win(){
//		ELO elo = new ELO(left, right);
//		elo.bothWin();
		System.out.println("BothWin");
		skip();
	}
	
	public void both_lose(){
//		ELO elo = new ELO(left, right);
//		elo.bothLose();
		System.out.println("BothLose");
		skip();
	}
	
	public void delete_left(){
//		left.getImage().delete();
//		set_left(queue.pop());
		System.out.println("deleteLeft");
	}
	
	public void delete_right(){
//		right.getImage().delete();
//		set_right(queue.pop());
		System.out.println("deleteRight");
	}
}
