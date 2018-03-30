import java.util.Scanner; 

import javax.swing.JOptionPane;

import kareltherobot.Directions; 
import kareltherobot.Robot; 
import kareltherobot.World; 


public class MakeADiamond  implements Directions{ 
public Robot r;
	

	public static void main(String[] args) { 
		MakeADiamond diamond = new MakeADiamond(); 
			
		
		//diamond.drawDiamond();
		diamond.drawDesiredCircle();
	}

	private void drawDesiredCircle() {
		
		String radius = JOptionPane.showInputDialog(null,
                "Radius for circle?");
		
		int radius2 = Integer.parseInt(radius);
		r = new Robot(radius2,1,North,radius2*10);
		
		JOptionPane.showMessageDialog(null,"The number you entered was "+ radius2);
	
		World.setSize(2*radius2, radius2*2);
		World.setVisible(true);
		for (int i=0; i<radius2 + 1; i++)
		{
			
			int b= Math.abs(radius2 - 1);
			int c= radius2 ^ 2;
			int a= (int) Math.sqrt(b - c);
			int x= r.avenue();
			int y= r.street();
			for (x< 1; y< radius2;)
			{
				
			
		 
			}
		}
		}
		
	private void goTo(int x, int y){
		while (this.r.avenue()<x);
		
	}
	private void turnRight(Robot r) {
		r.turnLeft();
		r.turnLeft();
		r.turnLeft();
		
	}

    private void faceNorth() {
    	if r.facingNorth(false){
    		
    	}
    }
	


}