package faceoff.competition;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.imgscalr.Scalr;

public class CompetitionPair {

	private Competitor left, right;
	
	private BufferedImage leftImageScaled, rightImageScaled;

	public CompetitionPair(Competitor left, Competitor right) {
		this.left = left;
		this.right = right;
	}
	
	public Competitor getLeft() {
		return left;
	}
	
	public Competitor getRight() {
		return right;
	}
	
	public void load_scaled_images(Dimension viewport) throws IOException{
		BufferedImage leftImage = left.lazyLoadImage(),
			rightImage = right.lazyLoadImage();

		double widthViewport = viewport.getWidth(),
			heightViewport = viewport.getHeight(),
		
			widthLeft = left.getWidth(),
			heightLeft = left.getHeight(),
			
			widthRight = right.getWidth(),
			heightRight = right.getHeight();
		
		double ratioLeft = (heightRight * widthViewport) / (heightLeft * widthRight + heightRight * widthLeft),
			ratioRight = (heightLeft * ratioLeft) / heightRight;
		
		if (ratioLeft >= 1.0){
			leftImageScaled = leftImage;
		} else {
			ratioLeft = Math.min(ratioLeft, heightViewport / heightLeft);
			int widthLeftScaled = (int)(widthLeft * ratioLeft),
				heightLeftScaled = (int)(heightLeft * ratioLeft);
			leftImageScaled = Scalr.resize(leftImage, widthLeftScaled, heightLeftScaled);
		}
		
		if (ratioRight >= 1.0){
			rightImageScaled = rightImage;
		} else {
			ratioRight = Math.min(ratioRight,  heightViewport / heightRight);
			
			int widthRightScaled = (int)(widthRight * ratioRight),
				heightRightScaled = (int)(heightRight * ratioRight);
			rightImageScaled = Scalr.resize(rightImage, widthRightScaled, heightRightScaled);
		}
	}
	
	public BufferedImage getLeftImageScaled() {
		return leftImageScaled;
	}
	
	public BufferedImage getRightImageScaled() {
		return rightImageScaled;
	}
}
