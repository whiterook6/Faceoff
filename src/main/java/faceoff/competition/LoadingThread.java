package faceoff.competition;

import java.awt.Dimension;
import java.io.IOException;

public class LoadingThread extends Thread {

	private CompetitionPair competitionPair;
	private Dimension viewport;
	
	public LoadingThread(CompetitionPair competitionPair, Dimension viewport) {
		this.competitionPair = competitionPair;
		this.viewport = viewport;
	}

	@Override
	public void run() {
		synchronized (this) {		
			try {
				competitionPair.load_scaled_images(viewport);
				System.out.println("Notifying");notify();
			} catch (IOException e) {}
		}
	}
	
	public CompetitionPair getCompetitionPair() {
		return competitionPair;
	}
}
