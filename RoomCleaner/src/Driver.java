import java.util.Scanner;

import javax.swing.JOptionPane;

import kareltherobot.*;


public class Driver implements Directions{
	public static int locationx2;
	public static int locationy2;
	public static String direction;
	Direction direction2;
	double beeperspicked;
	int length;
	int width;
	int distance;
	int biggestpile=0;
	int newpile;
	double area= width*length;
	double piles=0;
	int avenue;
	int street;
	double percdirty;
	double avg;
	int x;
	int y;
	int x2;
	int y2;
	Direction directiontoface;
	int bigpilex;
	int bigpiley;
	int avenue2;
	int street2;

	Robot r ;
	/**length
	 * @param args
	 *///
	public static void main(String[] args) {
		// LEAVE THIS ALONE!!!!!!
		Driver d = new Driver();
		d.getInfo();
		d.cleanRoom();
		d.printResults();
	}

	private void printResults() {
		// A bunch of System.out.prints go here
		JOptionPane.showMessageDialog(null, "Number of piles was " + piles );
		JOptionPane.showMessageDialog(null, "Area of room was " + area);
		JOptionPane.showMessageDialog(null, "Number of beepers was " + beeperspicked );
		JOptionPane.showMessageDialog(null, "Largest pile of beepers was " + biggestpile );
		JOptionPane.showMessageDialog(null, "Location of the largest pile was " + x + "," + y );
		JOptionPane.showMessageDialog(null, "Average pile size was " + avg );
		JOptionPane.showMessageDialog(null, "The percent dirty was " + percdirty + "%" );
	}


	private void cleanRoom() {
		// all the code that cleans and counts goes here
		// obviously, lots more here
		r= new Robot(locationy2,locationx2,direction2,0);
		beeperspicked= 0;

		gotoCorner();
		findLength();
		findWidth();
		area= width*length;
		for (int e=length-1; e>0; e--){
			cleanRow();
			goToNextRow();


		}

		percdirty= (double) (piles/area) *100;
		avg= beeperspicked/piles;
		gotoLeftCorner();
		x= bigpilex-avenue2;
		y= bigpiley-street2;
	}




	// my robot is

	private void gotoLeftCorner() {
		while (!r.facingWest()) {
			r.turnLeft();
		}
		while (r.frontIsClear()) {
			r.move();
		}
		r.turnLeft();

		while (r.frontIsClear()) {
			r.move();
		}
		avenue2= r.avenue();
		street2= r.street();

	}

	private void goToNextRow() {
		// reached a wall
		while (!r.facingEast()) {
			r.turnLeft(); 
		}
		// robot is facing east
		r.move(); 
		while (!r.facingNorth()){
			r.turnLeft(); 
		}

		for (int i=width-1; i>0; i--)  {
			r.move();


		}
		pickupBeepersAndCount();
	}


	private void findWidth() {
		width= 1;
		while (!r.facingNorth()) {
			r.turnLeft();
		}
		while (r.frontIsClear()) {
			r.move();
			width++;
		}

	}

	private void findLength() {
		length= 1;
		while (!r.facingWest()) {
			r.turnLeft();
		}
		while (r.frontIsClear()) {
			r.move();
			length++;
		}
	}

	private void gotoCorner() {
		while (!r.facingEast()) {
			r.turnLeft();
		}
		while (r.frontIsClear()) {
			r.move();
		}
		r.turnLeft();
		r.turnLeft();
		r.turnLeft();
		while (r.frontIsClear()) {
			r.move();
		}

	}

	private void cleanRow() {
		while (!r.facingSouth()) {
			r.turnLeft();
		}
		//robot is now facing south
		for (int i=width-1; i>0; i--)  {
			r.move();
			pickupBeepersAndCount();

		}



	}

	private void pickupBeepersAndCount() {
		newpile=0;
		while (r.nextToABeeper()) {
			r.pickBeeper();
			beeperspicked++;
			newpile++;
			if (newpile> biggestpile) {
				biggestpile= newpile;
				bigpilex= r.avenue();
				bigpiley= r.street();
				//				getDirection();
				//				findY();
				//				findX();
				//				goBack();



			}

		}
		if (newpile>0) {
			piles++;
		}
	}

	private void getDirection() {
		if (r.facingNorth()) {
			directiontoface= North;
		}
		else if (r.facingEast()) {
			directiontoface= East;
		}
		else if (r.facingSouth()) {
			directiontoface= South;
		}
		else if (r.facingWest()) {
			directiontoface= West;
		}

	}

	private void goBack() {
		while (!r.facingEast()) {
			r.turnLeft();
		}
		while (x2>0) {
			r.move();
			x2--;
		}
		while (!r.facingNorth()) {
			r.turnLeft();
		}
		while (y2>0) {
			r.move();
			y2--;
		}
		while (directiontoface= North) {
			while (!r.facingNorth()) {
				r.turnLeft();
			}
		}
		while (directiontoface= East) {
			while (!r.facingEast()) {
				r.turnLeft();
			}
		}
		while (directiontoface= South) {
			while (!r.facingSouth()) {
				r.turnLeft();
			}
		}
		while (directiontoface= West) {
			while (!r.facingWest()) {
				r.turnLeft();
			}
		}
	}

	private void findX() {
		while (!r.facingWest()) {
			r.turnLeft();
		}
		while (r.frontIsClear()) {
			r.move();
			x++;
		}
		x2=x;

	}

	private void findY() {
		while (!r.facingSouth()) {
			r.turnLeft();
		}
		while (r.frontIsClear()) {
			r.move();
			y++;
		}
		y2=y;

	}

	private void getInfo() {
		// this method acquires the starting street, avenue and direction
		// of the robot from the user.  Also it inputs the name of the world
		// file.  It then opens the world file and creates the robot
		String worldname = JOptionPane.showInputDialog("World Name?", "basicRoom.wld");
		String locationx = JOptionPane.showInputDialog("X Coordinate?");
		locationx2 = Integer.parseInt(locationx);
		String locationy = JOptionPane.showInputDialog("Y Coordinate?");
		locationy2 = Integer.parseInt(locationy);
		String direction = JOptionPane.showInputDialog("Direction to face?");
		if (direction.equals("North")) {
			direction2= North;
		}
		else if (direction.equals("East")) {
			direction2= East;
		}
		else if (direction.equals("West")) {
			direction2= West;
		}
		else if (direction.equals("South")) {
			direction2= South;
		}
		else
		{
			direction2= North;
		}

		String wrldName = worldname;



		World.readWorld(wrldName);
		//set world size?
		World.setVisible(true);
		World.setDelay(5);
	}

}
