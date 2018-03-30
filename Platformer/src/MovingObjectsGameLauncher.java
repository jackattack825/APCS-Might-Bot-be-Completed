import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Map;

import javax.swing.JFrame;

public class MovingObjectsGameLauncher {

	public static void main(String[] args) {
		JFrame gameFrame = new JFrame();

		gameFrame.setBackground(new Color(0,0,0));
		
		MovingObjectsPanel mop = new MovingObjectsPanel();
		gameFrame.add(mop);
		gameFrame.pack();
		gameFrame.setVisible(true);
		gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
	}

}
