import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class Simulator {

	public Timer simulatorTimer = null;

	public Simulator(KlondikeBoard b) {
		simulatorTimer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				simulatorTimeElapsed++;
				executeMove();
			}
		});
	}

	public void executeMove() {

	}
}
