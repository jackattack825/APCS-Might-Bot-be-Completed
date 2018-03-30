import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DeckStack extends Stack {

	public DeckStack(List<Card> cardsList, int locX, int locY) {
		super(cardsList, locX, locY);
		moveX = x;
		moveY = y;
	}

	// public boolean isClicked(){
	// if(isSelected==true){
	// System.out.println("yo");
	// return true;
	// }
	// return false;
	// }

	@Override
	public boolean isLegal() {
		if (cards.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean clickWithinBounds(MouseEvent click) {
		int clickX = click.getX();
		int clickY = click.getY();
		if (clickX <= x + 50 && clickY <= y + 80) {
			System.out.println("ok");

			return true;
		}
		return false;
	}

	@Override
	public void pressAction(MouseEvent press) {
			isSelected = true;
			System.out.println("pressed");
	}

	@Override
	public List<Card> successReleaseAction() {
		List<Card> newCard = new ArrayList<>();
		if (cards.size() > 0) {
			newCard.add(cards.remove(cards.size() - 1));
			System.out.println("new: " + newCard.size());
		}
		return newCard;
	}

	@Override
	public void failReleaseAction() {
		isSelected = false;
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
		if (cards.size() > 0) {
			Card a = cards.get(cards.size() - 1);
			a.draw(g, moveX + 10, moveY);
		}
	}

}
