import java.net.URL;

import javax.swing.ImageIcon;

public class Player extends GameObject{

	final static int WIDTH=40;
	final static int HEIGHT=70;
	final static int jump_vel=6;
	boolean jump=false;
	boolean falling=false;

	public Player(int x, int y) {
		super(MovingObjectsPanel.WIDTH/2-WIDTH/2, MovingObjectsPanel.HEIGHT/2 -HEIGHT/2, WIDTH, HEIGHT);
		loadImage();
	}
	@Override
	public void move()
	{
		//mainly only jump
	}
	private void loadImage()
	{
		try{
			//need to specify main player image
			img_url = getClass().getResource("f");
			img = new ImageIcon(img_url).getImage();	
		}
		catch(Exception e){}
	}
	public void jump(){
		System.out.println("jumping");
	}
}
