package faceoff.competition;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

import faceoff.elo.ELO;

public class Competitor {

	private File file;
	private BufferedImage image;
	private double score;
	private Dimension dimension;
	private boolean hasFought = false;

	public Competitor(File file) {
		this.file = file;
		this.score = ELO.DEFAULT_SCORE;
	}

	public File getImage() {
		return file;
	}
	
	private static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        bimage.getGraphics().drawImage(img, 0, 0 , null);
        bimage.getGraphics().dispose();

        return bimage;
    }
	
	public BufferedImage lazyLoadImage() throws IOException {
		if (image == null){
			Image tmp = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
			MediaTracker tracker = new MediaTracker(new JPanel());
			tracker.addImage(tmp, 0);
			try {
				tracker.waitForID(0);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			image = Competitor.toBufferedImage(tmp);
			dimension = new Dimension(image.getWidth(), image.getHeight());
		}
		
		return image;
	}
	
	public void clearCache(){
		if (image == null){
			return;
		}
		
		image.flush();
		image = null;
	}
	
	public int getWidth() throws IOException{
		if (dimension == null){
			lazyLoadImage();
		}
		
		return dimension.width;
	}
	
	public int getHeight() throws IOException{
		if (dimension == null){
			lazyLoadImage();
		}
		
		return dimension.height;
	}

	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public void setFought(){
		this.hasFought = true;
	}
	
	public boolean getHasFought(){
		return this.hasFought;
	}
	
	@Override
	public String toString() {
		if (null != this.dimension){
			return String.format("%s [%dx%d]", this.file.getName(), this.dimension.width, this.dimension.height);	
		} else {
			return this.file.getName();
		}
	}
}
