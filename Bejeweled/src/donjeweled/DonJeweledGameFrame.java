package donjeweled;


import javax.swing.JFrame;


public class DonJeweledGameFrame extends JFrame {
	
	
	public DonJeweledGameFrame() {
		super("Donjewled");// What does the String do?
		
		//this.setResizable(false);
		this.add(new DonJeweledPanel());// adds the JPanel to the JFrame.  This is proper
		
		
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);// ALWAYS DO THIS!!!!


		pack();// this makes sure the JFrame is big enough to display all the component within
		setVisible(true);// show this frame
	}

	
}
