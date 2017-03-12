package faceoff.competition;

import java.io.File;

import faceoff.elo.ELO;

public class Competitor {

	private File image;
	private double score;

	public Competitor(File image) {
		this.image = image;
		this.score = ELO.DEFAULT_SCORE;
	}

	public File getImage() {
		return image;
	}

	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
}
