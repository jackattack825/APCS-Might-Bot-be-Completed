/**
 * This is a class that tests the Card class.
 */
public class CardTester {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		Card x= new Card("5", "clubs", 5);
		System.out.println(x.toString());
		Card y= new Card("5", "clubs", 5);
		Card z= new Card("king", "clubs", 14);
		System.out.println(x.matches(y));
		System.out.println(x.matches(z));
		
	}
}
