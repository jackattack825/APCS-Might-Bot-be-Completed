import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class GoalStack extends Stack {


	int indexFirstSelected;
	
	public GoalStack(List<Card> cardsList, int locX, int locY) {
		super(cardsList, locX, locY);
	}

	@Override
	public boolean isLegal() {
		for (int i = 0; i < cards.size() - 1; i++) {
			Card c1 = cards.get(i);
			Card c2 = cards.get(i + 1);
			boolean orderCorrect = c1.pointValue() + 1 == c2.pointValue();
			
			if (!(orderCorrect)) {
				return false;
			}
		}
		return true;
	}


	@Override
	protected boolean clickWithinBounds(MouseEvent click) {
		int clickX = click.getX();
		int clickY = click.getY();
		if (x <= clickX && clickX <= (x + cardWidth) &&  clickY <= (y  + cardHeight) && clickY >= (y)) {
			return true;
		}
		return false;
	}

	@Override
	public void pressAction(MouseEvent press) {
		if (clickWithinBounds(press)) {
			isSelected = true;
			for (int i = cards.size() - 1; i >= 0; i--) {
				System.out.println(press.getY() + "|" + (i * cardSpacing));
				if (press.getY() > y + i * cardSpacing) {
					indexFirstSelected = i;
					System.out.println("SELECTED: " + indexFirstSelected);
					break;
				}
			}
			
		}
	}



	@Override
	public void dragAction(MouseEvent drag, MouseEvent initialPress) {
		// instead clickWithinBounds(initialPress)? or (drag)?
		// perhaps setFaceUpVariablesToNormal is unnecessary or needs to be
		// changed
		System.out.println(isSelected);
		if (clickWithinBounds(initialPress) && isSelected) {
			int pressX = initialPress.getX();
			int pressY = initialPress.getY();
			int gapX = pressX - x;
			int gapY = pressY - y;

			moveX = drag.getX() - gapX;
			moveY = drag.getY() - gapY;
		}
	}

	@Override
	public void draw(Graphics g) {
		if (cards.size() >= 2) {
			cards.get(cards.size() - 2).draw(g, x, y);
		}

	}

	@Override
	public void drawMovingCards(Graphics g) {
		if(cards.size()!=0){
			cards.get(cards.size()-1).draw(g, moveX, moveY);
		}
		System.out.println(cards.size());

	}

	@Override
	public List<Card> successReleaseAction() {
		List<Card> card = new ArrayList<>();
		cards.get(cards.size()-1).setFaceUp();
		card.add(cards.get(cards.size()-1));
		cards.remove(cards.size()-1);
		isSelected= false;
		moveX=x;
		moveY=y;
		return card;
	}

	@Override
	public void failReleaseAction() {
		moveX=x;
		moveY=y;
		isSelected= false;
	}

	@Override
	public void update() {



	}


}
