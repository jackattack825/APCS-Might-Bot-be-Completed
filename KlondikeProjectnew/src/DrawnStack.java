import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.List;

public class DrawnStack extends Stack{

	//Will dragging a horizontal line of pixels when this stack is empty cause a bug?
	public DrawnStack(List<Card> cardsList, int locX, int locY) {
		super(cardsList, locX, locY);
		
		update();
	}
	
	@Override
	public boolean isLegal() {
		
		return false;
		
	}

	@Override
	protected boolean clickWithinBounds(MouseEvent click) {
		
		return false;
		
	}

	@Override
	public void pressAction(MouseEvent press) {
	}

	@Override
	public List<Card> successReleaseAction() {
		
		return null;
		
	}

	@Override
	public void failReleaseAction() {
	}

	@Override
	public void update() {
	}

	@Override
	public void dragAction(MouseEvent drag, MouseEvent initialPress) {
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void drawMovingCards(Graphics g) {
		if(cards.size()>0){
			Card a = cards.get(cards.size()-1);
//			a.draw(g, moveX+(cardWidth*, moveY);
		}
	}

}
