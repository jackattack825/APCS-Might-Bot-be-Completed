import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawnStack extends DeckStack {

	private int first;
	private Card a;

	// Will dragging a horizontal line of pixels when this stack is empty cause
	// a bug?
	public DrawnStack(List<Card> cardsList, int locX, int locY) {
		super(cardsList, locX, locY);
	}

	@Override
	public boolean isLegal() {
		if (cards.size() <= 3) {
			return true;
		}
		return false;

	}

	@Override
	public boolean clickWithinBounds(MouseEvent click) {
		int clickX = click.getX();
		int clickY = click.getY();
		if (clickX <= x + 50 && clickY <= y + (80 * 2)) {
			System.out.println("within");

			return true;
		}
		return false;
	}

	@Override
	public void pressAction(MouseEvent press) {
		isSelected = true;
	}

	@Override
	public List<Card> successReleaseAction() {
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
		if (clickWithinBounds(initialPress)) {
			int pressX = initialPress.getX();
			int pressY = initialPress.getY();
			moveX = drag.getX();
			moveY = drag.getY();
		}
	}

	@Override
	public void draw(Graphics g) {
	}

	@Override
	public void drawMovingCards(Graphics g) {
		if (cards.size() > 0) {
			// Card a = cards.get(cards.size()-1);
			// a.draw(g, moveX+10, moveY+200);
			// }
			for (int i = 1; i < cards.size(); i++) {
				// System.out.println("card size: " + newCard.size());
				a = cards.get(i);
				a.setFaceUp();
				a.draw(g, moveX + 10, moveY + 150 + (i * cardSpacing));
			}
		}
	}
}
