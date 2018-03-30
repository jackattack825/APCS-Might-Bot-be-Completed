import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class KlondikeBoard {

	// Maybe later implement code to ensure playable/solvable Game

	FullDeckStack fullDeck = new FullDeckStack();

	List<Stack> stacks = new ArrayList<>();

	public MouseEvent initialClick;

	public KlondikeBoard() {
		setSizes();
		createStacks();
	}

	public void setSizes() {
		Card.setWidthAndHeight(70, 95);
	}

	public void playCardSlideSound() {
		AudioStream cardSlideSound = null;
		try {
			URL url = getClass().getResource("res/sounds/cardSlide2.wav");
			cardSlideSound = new AudioStream(url.openStream());
		} catch (Exception e) {
			System.out.println("Problem opening a sound");
			e.printStackTrace();
		}
		AudioPlayer.player.start(cardSlideSound);
	}

	public void createStacks() {
		/*
		 * 0-6 PlayStacks 7-10 GoalStacks 11 DeckStacks 12 DrawnStacks
		 */
		
		/* SOLITAIRE FORMATTING (O = faceUp, X = faceDown
		 * ..............................................................................
		 * .XXXX......OOOO..OOOO..OOOO..OOOO..OOOO..OOOO..OOOO..OOOO......OOOO...........
		 * .XXXX......OOOO..OOOO..OOOO..OOOO..OOOO..OOOO..OOOO..OOOO......OOOO...........
		 * .XXXX......OOOO..OOOO..OOOO..OOOO..OOOO..OOOO..OOOO..OOOO......OOOO...........
		 * +----+........................................................................
		 * |____|.........................................................OOOO...........
		 * |____|.........................................................OOOO...........
		 * |OOOO|.........................................................OOOO...........
		 * |OOOO|........................................................................
		 * |OOOO|.........................................................OOOO...........
		 * +----+.........................................................OOOO...........
		 * ...............................................................OOOO...........
		 * ..............................................................................
		 * ...............................................................OOOO...........
		 * ...............................................................OOOO...........
		 * ...............................................................OOOO...........
		 * ..............................................................................
		 * ..............................................................................
		 */
		
		// PlayStacks
		for (int i = 1; i <= 7; i++) {
			stacks.add(new PlayStack(fullDeck.dealRandomCardList(i), Card.getCardWidth()+100 + 150 * i, 100));
			// stacks.add(new
			// PlayStack(fullDeck.dealRandomCardList(i),150*i,0));
		}
		for (int i = 0; i < 4; i++) {
			stacks.add(new GoalStack(fullDeck.dealRandomCardList(2), 400, 400+220*i));
		}
		stacks.add(new DeckStack(fullDeck.dealRandomCardList(10),100,100));
		stacks.add(new DrawnStack(fullDeck.dealRandomCardList(1),100,300));
	}

	public void pressedAt(MouseEvent press) {
		initialClick = press;
		for (Stack s : stacks) {
			s.pressAction(press);
		}
	}

	public void releasedAt(MouseEvent release) {
		// finds index of selected Card
		int selectedIndex = -1;
		for (int i = 0; i < stacks.size(); i++) {
			if (stacks.get(i).getIsSelected()) {
				selectedIndex = i;
				break;
			}
		}

		for (Stack s : stacks) {
			if (s.clickWithinBounds(release) && s != stacks.get(selectedIndex)) {
				System.out.println("SUCCESS RELEASE");
				// transfer
				s.addCards(stacks.get(selectedIndex).successReleaseAction());
				if (!s.isLegal()) {
					System.out.println("ILLEGAL MOVE");
					stacks.get(selectedIndex).addCards(s.successReleaseAction());
				} else {
					s.update();
					stacks.get(selectedIndex).update();
					playCardSlideSound();
				}
				break;
			}
		}

		if (selectedIndex != -1) {
			System.out.println("FAIL RELEASE");
			stacks.get(selectedIndex).failReleaseAction();
			return;
		}
	}

	public void draggedAt(MouseEvent drag) {
		for (Stack s : stacks) {
			s.dragAction(drag, initialClick);
		}
	}

	public void draw(Graphics g) {
		for (Stack s : stacks) {
			s.draw(g);
		}
		for (Stack s : stacks) {
			s.drawMovingCards(g);
		}
	}
}
