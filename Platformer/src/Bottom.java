import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Bottom extends GameObject {
	
	private int locx, locy, height, width;
	BufferedImage img;
	
	public Bottom(int x, int y, int wid, int ht) {
		super(x, y, wid, ht);
		this.setImage(img, null);
	}
	public void draw(Graphics g){
		g.drawImage(img, locx, locy, null);
		
	}
	public BufferedImage setImage(BufferedImage img, String file){
		try{
			img = ImageIO.read(this.getClass().getResourceAsStream("res/" + file+ ".png"));
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return img;
	}
	public Rectangle getHitBox(){
		return new Rectangle(this.locx, this.locy,this.width, this.height);
	}
	public boolean onGround(GameObject gmobj){
		if(gmobj.getHitBox().intersects(this.getHitBox()) && gmobj.x>= this.locx && gmobj.x<= this.locx + this.width)
			return true;
		return false;
	}
}
