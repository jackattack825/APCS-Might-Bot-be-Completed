import java.awt.Color;

import javax.swing.JFrame;

public class Launcher {
	
	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();

		gameFrame.setBackground(new Color(0,0,0));
		
		Mandel mop = new Mandel();
		gameFrame.add(mop);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
	}
}
