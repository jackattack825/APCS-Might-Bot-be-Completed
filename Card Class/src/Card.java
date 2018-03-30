import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;


public class Card {
	String suit;
	String rank;
	int pointValue;
	boolean isRed;
	boolean isRightSideUp;
	static Image myImg;
	
	public Card (String su, String ra, int point, boolean isRe, boolean isRightSide){
		suit=su;
		rank=ra;
		isRed=isRe;
		isRightSideUp=isRightSide;
		pointValue= point;
	}
	
	public String getSuit(){
		return suit;
	}
	
	public String getRank(){
		return rank;
	}
	
	public int getPointValue(){
		return pointValue;
	}
	
	public boolean isRed(){
		return isRed;
	}
	
	public boolean isRightSideUp(){
		return isRightSideUp;
	}
	
	public void setSuit(String su){
		suit=su;
	}
	
	public void setRank(String ra){
		rank=ra;
	}
	
	public void setPointValue(int po){
		pointValue=po;
	}
	
	public void setColorRed(boolean isRe){
		isRed=isRe;
	}
	
	public void setRightSideUp(boolean isRightSide){
		isRightSideUp=isRightSide;
	}
	
	public void draw(Graphics g, int x, int y, int w, int h) {
		this.openImage();
		g.drawImage(myImg, x, y, w,h,null);
	}
	
	protected void openImage() {
		boolean x= false;
		if(myImg== null){
			try {
				String faceCard = "";
				URL url;
				if(this.getPointValue()==11 || this.getPointValue()==12 || this.getPointValue()==13)
					x=true;
				if(x)
					url = getClass().getResource("src/cards/"+ faceCard + this.getSuit() +".GIF");
				else
					url = getClass().getResource("src/cards/"+ this.getPointValue() + this.getSuit() +".GIF");
				myImg = ImageIO.read(url);
				System.out.println(myImg);
			} catch (IOException e) {
				System.out.println("Problem opening the **.jpg");
				e.printStackTrace();
			}
		}
	}
}
