import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class GameObject {
	protected int x,y,width, height;
	protected int direction=0;
	protected Image img;
	protected URL img_url;
	
	final static int speed=3;
	
	public GameObject(int x, int y, int wid, int ht) {
		this.x=x;
		this.y=y;
		this.width = wid;
		this.height=ht;
	}
	public int getDir(){
		return direction;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void move()
	{
		switch(direction)
		{
		case 0:
			break;
		case 1:
			x+=speed;
			break;
		case 2:
			x-=speed;
			break;
		}
	}
	public void setDir(int dir)
	{
		this.direction = dir;
	}
	public void draw(Graphics g)
	{
		if(img!=null)
			g.drawImage(img,x,y,width,height,null);
		else
			g.fillRect(x, y, width, height);
	}
	public Rectangle getHitBox() {
		return new Rectangle(x,y,width,height);
	}
	
}
