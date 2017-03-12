package faceoff.competition;

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
	private BufferedImage thumbnail;
	private double score;

	public Competitor(File image) {
		this.file = image;
		this.score = ELO.DEFAULT_SCORE;
	}

	public File getImage() {
		return file;
	}
	
	public BufferedImage getThumbnail(int maxWidth, int maxHeight) throws IOException{
		Image img = Toolkit.getDefaultToolkit().getImage( file.getAbsolutePath() );
		loadCompletely(img);
        return toBufferedImage(img);

	}

	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	 /**
     * Since some methods like toolkit.getImage() are asynchronous, this
     * method should be called to load them completely.
     */
    private static void loadCompletely (Image img)
    {
        MediaTracker tracker = new MediaTracker(new JPanel());
        tracker.addImage(img, 0);
        try {
            tracker.waitForID(0);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
	
	private static BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        bimage.getGraphics().drawImage(img, 0, 0 , null);
        bimage.getGraphics().dispose();

        return bimage;
    }
}
