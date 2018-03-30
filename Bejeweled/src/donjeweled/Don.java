package donjeweled;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Don {
	protected Image img;
	private int type;
	
	public Don(int t) {
		type = t;
		openImage();
	}
	protected abstract void openImage();
	
	public boolean equals(Object x) {
		try {
			Don d = (Don) x;
			return d.type == this.type;
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void draw(Graphics g, int x, int y, int w, int h) {
		g.drawImage(img, x, y, w,h,null);
	}

}
