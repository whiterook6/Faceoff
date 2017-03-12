package faceoff.competition;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import faceoff.elo.ELO;

public class Competitor {

	private File file;
	private BufferedImage thumbnail;
	private double score;

	public Competitor(File image) {
		this.file = image;
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
