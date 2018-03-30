import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class GameMap {
	//ArrayLists for the types go here
	ArrayList<GameObject> go;
	Player player;
	/*
	 * TODO Create Character
	 * Incorporate Jump / Collision
	 * Movement of Character
	 * 
	 * Create Ground, platforms (Map)
	 * 
	 * Enemies (Mushroom man)
	 * 
	 * Scrolling Map - along with key press
	 * 		hide parts of map when passed
	 * 
	 */
	Image backgroundImage;
	
	public GameMap() {
		go = new ArrayList<GameObject>();
		openBackgroundImage();
		makeCharacters();
		makeMap();
	}
	public void openBackgroundImage(){
		
	}
	public void tick()
	{
		for(GameObject gobject: go)
		{
			gobject.move();
		}
	}
	public void makeMap()
	{
		//TODO make ground, platforms, holes
		//add them to the go arraylist
		
		//this one below is random
		go.add(new GameObject(0,MovingObjectsPanel.HEIGHT/2+Player.HEIGHT/2+5,500,50));
	}
	private void makeCharacters()
	{
		player = new Player(20,20);
		//make enemies - like mushroom things
	}
	public void setPlayerDir(int dir)
	{
		//this will actually move the things in (ArrayList) go,
		//not the player
		if(dir==3)
		{
			player.jump();
		}
		else{
			for(GameObject gobject:go)
			{
				gobject.setDir(dir);
			}
		}
	}
	void draw(Graphics g){
		player.draw(g);
		for(GameObject gobject : go)
		{
			gobject.draw(g);
		}
	}
}
