/**
 * This is a class that tests the Deck class.
 */
public class DeckTester {

	/**
	 * The main method in this class checks the Deck operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		String [] ranks= {"king", "queen", "jack", "9", "8"};
		String [] suit= {"spades", "clubs", "hearts", "diamonds"};
		int [] values= {14,13,12,9,8};
		Deck test= new Deck(ranks, suit, values);
		System.out.println(test.isEmpty());
	}
}
