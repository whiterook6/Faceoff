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
		return file;
	}
	
	private BufferedImage getCompatibleImage(int w, int h) {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		GraphicsConfiguration gc = gd.getDefaultConfiguration();
		BufferedImage compatibleImage = gc.createCompatibleImage(w, h);
		return compatibleImage;
	}
	
	private void loadThumbnail(int maxWidth, int maxHeight) throws IOException{
		BufferedImage image = ImageIO.read(file);
		int imageWidth = image.getWidth();
		int imageHeight= image.getHeight();
		if (imageWidth <= maxWidth && imageHeight <= maxHeight){
			thumbnail = image;
			return;
		}

		double widthRatio = (double)maxWidth / (double)imageWidth;
		double heightRatio = (double)maxHeight / (double)imageHeight;
		double ratio = Math.min(widthRatio, heightRatio);
		int newWidth = (int)(imageWidth * ratio);
		int newHeight = (int)(imageHeight * ratio);
		
		thumbnail = getCompatibleImage(newWidth, newHeight);
		Graphics2D g2d = thumbnail.createGraphics();
		AffineTransform at = AffineTransform.getScaleInstance(widthRatio,heightRatio);
		g2d.drawRenderedImage(image, at);
		g2d.dispose();
	}
	
	public BufferedImage getThumbnail(int maxWidth, int maxHeight) throws IOException{
		if (thumbnail == null || thumbnail.getWidth() > maxWidth || thumbnail.getHeight() > maxHeight){
			loadThumbnail(maxWidth, maxHeight);
		}
		
		return thumbnail;
	}

	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
}
