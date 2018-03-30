package donjeweled;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;



public class DonJeweledPanel extends JPanel {

	private static final Dimension PREFERRED_DIM = new Dimension(800,1200);

	private final static int COLS = 8,ROWS = 8;

	private static final int SQ= (int) (PREFERRED_DIM.getWidth()/COLS);
	private JProgressBar timeLeftBar;							                 

	private static final int TOP_PADDING =0; //(int) (PREFERRED_DIM.getHeight()-ROWS*SQ);

	private List<Image> backGroundList = new ArrayList();
	private int TEXT_YC = 20;
	private int timeElapsed;
	private Timer timer;
	private Don[][] donGrid;
	private int[][] clickState;
	private MouseEvent lastClick;
	private int secondClickRow= -1;
	private int secondClickCol= -1;
	private int firstClickRow=-1,firstClickCol=-1;
	private boolean firstClick = true;
	private final Don[] picArr = {new DrDre(), new Hall(), new Hanson(), new Sam(), new Simms()};

	public DonJeweledPanel() {
		// See below for the proper way to open images and other types of
		// resources
		System.out.println("Opening the panel!!");
		openImages();
		this.setPreferredSize(PREFERRED_DIM);
		// See the method below for the BEST way to set up ways to interact with 
		// mouse events.  Similar to interact with dragging and moving mouse
		setUpClickListener();
		setUpGame();
		setUpTimer();
		setVisible(true);
		timer.start();
	}


	private void setUpGame() {
		// set up the grid.
		// add Dons to the Grid
		donGrid = new Don[ROWS][COLS];
		for(int x=0; x<donGrid.length; x++){
			for(int y=0; y<donGrid[x].length; y++){

				donGrid[x][y] = picArr[(int) (Math.random()*5)];
			}
		}
		addDons();
	}

	private void setUpTimer() {
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timeElapsed++;
				updateProgressBar();
				repaint();
			}

		});

	}

	// We would like to see a progress bar get smaller and smaller
	// as time elapses.
	protected void updateProgressBar() {

	}


	private void setUpClickListener() {
		// Whoever has focus is who can interact with mouse and keyboard, etc
		this.requestFocusInWindow();

		// similar to having an entity ready to interact with the Mouse
		this.addMouseListener(new MouseListener() {
			/*
			 * If you want to detect mouse dragging, then use a mouseMotionListener
			 */
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// I like to avoid using this one because if you are moving
				// the mouse while you are trying to click, it sometimes doesn't
				// register the click.  A click is a press and release at the 
				// same location

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// when the mouse enters the panel, this is called

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// duh...

			}

			@Override
			public void mousePressed(MouseEvent click) {
				// use this method when you wish to detect a click
				System.out.println(click);

				// here are some things you can do with a MouseEvent
				System.out.println(click.getX());
				System.out.println(click.getPoint());
				System.out.println(click.getButton());// try clicking left and right buttons

				//// So... What should we do when we detect a click?
				// This is a good way to isolate the clicking and the behavior
				// to be done when the JPanel is clicked
				int col= click.getX()/ SQ;
				int row= (click.getY()- TOP_PADDING)/ SQ;
				if(click.getButton()==1)
					leftClick(click, col, row);
				if(click.getButton()==3)
					rightClick(click);
				clickedAt(click);
			}

			private void rightClick(MouseEvent click) {


			}

			private void leftClick(MouseEvent click, int col, int row) {
				if(firstClick){
					firstClick = false;// so next click will be second click
					firstClickCol = col;
					firstClickRow = row;
				}
				else{
					secondClickRow = row;
					secondClickCol = col;
					if(canSwap()){
						Don x= donGrid[firstClickRow][firstClickCol];
						Don y= donGrid[secondClickRow][secondClickCol];
						donGrid[firstClickRow][firstClickCol]= y;
						donGrid[secondClickRow][secondClickCol]= x;
						while(makes3InARow()){//marks the Dons that can be removed
							removeMarked();
							dropDons();
							addDons();
						}
						firstClick = true;
					}
					// so next click will be second click
				}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// use this to find out when the mouse was released

			}

		});

	}


	protected boolean canSwap() {
		if(makes3InARow()){
			if((Math.abs(firstClickRow-secondClickRow)==1 && Math.abs(firstClickCol-secondClickCol)==0) || (Math.abs(firstClickCol-secondClickCol)==1 && Math.abs(firstClickRow-secondClickRow)==0))
				return true;
		}
		return false;
	}


	protected void removeMarked() {
		// TODO Auto-generated method stub

	}


	protected boolean makes3InARow() {
		for(int i=0; i<donGrid.length;i++){
			for(int j=0; j<donGrid[0].length;j++){
				try{
					if(donGrid[i][j]==donGrid[i+1][j] && donGrid[i][j]==donGrid[i+2][j]){
						donGrid[i][j]=null;
						donGrid[i+1][j]=null;
						donGrid[i+2][j]=null;
						return true;
					}
					if(donGrid[i][j]==donGrid[i][j+1] && donGrid[i][j]==donGrid[i][j+2]){
						donGrid[i][j]=null;
						donGrid[i][j+1]=null;
						donGrid[i][j+2]=null;
						return true;
					}
				}
				catch(Exception e){
				}
			}
		}
		return false;
	}


	protected void clickedAt(MouseEvent click) {
		// This is called when the panel is clicked.  What should we do?

		repaint();
	}

	// this is called any time new Dons need to be added
	private void addDons() {
		// in case there are some empty spots, tell all the Dons in the 
		// grid to check to see if any empty spots below them
		dropDons();
		// add Dons to each column and have them drop.  Repeat until col is full.
		// repeat for each column


	}

	// Starting at bottom, if there are any empty spots, have those spots get filled by 
	// the Dons above, if there are any non-null Dons above
	private void dropDons() {


	}


	private void printDons() {
		for(Don[] row: donGrid) {
			for(Don d:row) {
				System.out.print(d);
			}
			System.out.println();
		}

	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//		System.out.println("painting the panel");
		drawBackground(g);
		for(int r = 0; r < donGrid.length; r++) {
			for(int c = 0; c< donGrid[0].length;c++) {
				Don d = donGrid[r][c];
				//				System.out.println(r+" , "+c+" is a "+d);
				if(d != null) {
					//					System.out.println("drawing "+d);
					d.draw(g,c*SQ,TOP_PADDING + SQ*r,SQ, SQ);
				}
			}
		}
		drawExtraStuff(g);
	}

	private void drawExtraStuff(Graphics g) {
		// TODO Auto-generated method stub

	}


	private void drawBackground(Graphics g) {
		// TODO Auto-generated method stub

	}


	private void openImages() {
		/*
		 *Only Open Images needed in this class that are needed
		 *by this class.  Images that each Don uses will be opened 
		 *in that Don class. 
		 **/
		try {
			URL url = getClass().getResource("res/images/simms.jpg");
			backGroundList.add( ImageIO.read(url));
		} catch (IOException e) {
			System.out.println("Problem opening the simms.jpg");
			e.printStackTrace();
		}
		try {
			URL url = getClass().getResource("res/images/DrDre.jpg");
			backGroundList.add( ImageIO.read(url));
		} catch (IOException e) {
			System.out.println("Problem opening the drDre.jpg");
			e.printStackTrace();
		}

	}

}
