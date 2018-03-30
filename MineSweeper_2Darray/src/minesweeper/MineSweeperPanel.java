package minesweeper;

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

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;



public class MineSweeperPanel extends JPanel {

	private static final int SQ = 25,// the width and height of a square
			                 H_PADDING = 10;

	private static final int TOP_PADDING = 80,
			                 BOTTOM_PADDING = 10;

	Image unClickedSquare, flaggedSquare;// Images to be displayed

	// this contains the mines.  zero means no mine, 1 means mine
	// using zeros and ones instead of booleans will make it easier 
	// to add up the number of mines surrounding a square.
	// when we know the size (when this panel is constructed) we will
	// create the grid to be the correct size and then fill it randomly
	// with the correct number of mines
	int[][] mineGrid;

	int[][] clickState;// 0 if unclicked, 1 if clicked, 2 if flagged
	
	// this will store the numbers that will be displayed when a 
	// square is clicked.  This will be created after creating the 
	// mineGrid, so that the correct numbers can be placed into it
	int[][] numbers;

	// This is how you make a 2D array and initialize it all in one step
	// this is a 2x3 matrix.  This first row represents the number of rows
	// for any gameboard, and the second represents the corresponding cols
	public final int[][] ROW_COL = {{10, 30, 50},
			{10, 16, 50}};
	// percentage of spaces with mines
	final double[] DIFF = {.125, .25, .6};
	// this is the sizeIndex that causes the user to input the dimensions
	final int CUSTOM_INDEX = 3;

	// more instance variables will be created!
	private boolean firstClick = true;

	private int diffIndex;
	private final Color[] COLOR_ARR = {Color.black,
			           new Color(200,0,0), new Color(100,25,25),
				       new Color(170,100,0), new Color(100,50,100),
				       new Color(70,200,70), new Color(80,0,200),
				       new Color(140,70,0), new Color(0,140,70)};
	
	private int sizeIndex;

	private int TEXT_YC = 20;

	private int flagsLeft ;

	private int timeElapsed;

	private Timer timer;


	public MineSweeperPanel(int diffIndex, int sizeIndex) {
		// See below for the proper way to open images and other types of
		// resources
		openImages();
		
		// this uses the two ints below to access ROW_COL and 
		// create the 2D array of ints
		//createGrid(diffIndex, sizeIndex);
		
		this.diffIndex = diffIndex ;
		this.sizeIndex =sizeIndex;
		createDummyGrid(diffIndex, sizeIndex);

		// base the size of this JPanel upon the dimension of the 2D array
		// which has just been initialized by the method above
		setPreferredSize(new Dimension(mineGrid[0].length*SQ+H_PADDING*2,mineGrid.length*SQ + TOP_PADDING+BOTTOM_PADDING));

		// See the method below for the BEST way to set up ways to interact with 
		// mouse events.  Similar to interact with dragging and moving mouse
		setUpClickListener();
		
		setUpTimer();
	}


	private void setUpTimer() {
		timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				timeElapsed++;
				repaint();
			}
			
		});
		
	}


	private void createDummyGrid(int diffIndex2, int sizeIndex2) {
		int[] row_col;
		if(sizeIndex == this.CUSTOM_INDEX) {
			row_col = createCustomSize();
		}
		else {
			row_col = new int[] {ROW_COL[0][sizeIndex], ROW_COL[1][sizeIndex]};
		}
		mineGrid = new int[row_col[0]][row_col[1]];
		clickState = new int[row_col[0]][row_col[1]];
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
				
				clickedAt(click);
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// use this to find out when the mouse was released

			}

		});

	}


	protected void clickedAt(MouseEvent click) {
		// This is called when the panel is clicked.  What should we do?
		if(click.getX() < H_PADDING || click.getY()<TOP_PADDING ||
			click.getX() > this.getWidth()-H_PADDING ||
			click.getY() > this.getHeight()-BOTTOM_PADDING)
			return;
		
		int col = (click.getX()-H_PADDING)/SQ;
		int row = (click.getY()-TOP_PADDING)/SQ;
		System.out.println("row: "+row+" col: "+col);
		
		
		
		if(click.getButton()==1) {
			leftClick(row, col);
		}
		else if(click.getButton()==3){
			rightClick(row,col);
		}
		repaint();
	}


	private void rightClick(int row, int col) {
		if(firstClick)
			return;
		
		if(clickState[row][col]==0) {
			clickState[row][col]=2;
			flagsLeft--;
		}
		else if(clickState[row][col]==2) {
			clickState[row][col]=0;
			flagsLeft++;
		}
		
	}


	private void leftClick(int row, int col) {
		// make sure the row and col make sense+
		if(row < 0 || col < 0 || 
		   row >= mineGrid.length||col >= mineGrid[0].length)
			  return;
		// row and col are good, so now what?
		if(firstClick) {
			firstClick = false;
			createGrid(row, col);
			timer.start();
		}
		
		
		
		
		if(clickState[row][col]!=0)
			return;
		
		
		
		
		
		
		
		
		// they have clicked something that should be revealed
		clickState[row][col]=1;
		
		if(mineGrid[row][col]==1)
			blowUp();
		else {
			// look at numbers
			if(numbers[row][col]== 0) {
				leftClick(row-1,col);
				leftClick(row+1,col);
				leftClick(row-1,col-1);
				leftClick(row+1,col-1);
				leftClick(row,col+1);
				leftClick(row,col-1);
				leftClick(row-1,col+1);
				leftClick(row+1,col+1);
			}
			
			
			
			
			
			
			
		}
		
	}


	private void blowUp() {
		// TODO Auto-generated method stub
		System.out.println("ouch!");
	}


	private void createGrid(int startRow, int startCol) {
		int[] row_col;
		if(sizeIndex == this.CUSTOM_INDEX) {
			row_col = createCustomSize();
		}
		else {
			row_col = new int[] {ROW_COL[0][sizeIndex], ROW_COL[1][sizeIndex]};
		}
		mineGrid = new int[row_col[0]][row_col[1]];
		
		System.out.println(mineGrid.length+"  by  "+mineGrid[0].length);

		int rows = mineGrid.length,
				cols = mineGrid[0].length;

		int mines = (int) (DIFF[diffIndex]*rows*cols);
		
		this.flagsLeft= mines;
		placeMines(mines, startRow, startCol);
		placeNumbers();
	}
	private void placeNumbers() {
		numbers = new int[mineGrid.length][mineGrid[0].length];
		for(int r = 0; r<numbers.length; r++) {
			for(int c = 0; c<numbers[0].length;c++) {
				if(mineGrid[r][c]==1)
					numbers[r][c] = -1;
				else {
					int count = 0;
					for(int dr = -1; dr <= 1; dr++) {
						for(int dc = -1; dc <= 1; dc++) {
							
							if(dr != 0 || dc != 0) {
								try {
									count+=mineGrid[r+dr][c+dc];
								}
								catch(Exception e) {
									
								}
							}
						}
					}
					
					numbers[r][c] = count;
				}
			}
		}
		printCount();
	}


	private void printCount() {
		for(int[] row:numbers) {
			for(int x:row) {
				System.out.print(x);
			}
			System.out.println();
		}
		
	}


	private void placeMines(int mines, int startRow, int startCol) {

		int rows = mineGrid.length,
				cols = mineGrid[0].length;
		for(int i = 0; i<mines; i++) {
			boolean placedMine = false;
			int r = (int) (Math.random()*rows);
			int c = (int) (Math.random()*cols);
			
			if(r != startRow || c != startCol) {
				if(mineGrid[r][c] == 0) {
					mineGrid[r][c] = 1;
					placedMine = true;
				}
			}
			if(!placedMine)
				i--;
		}
		//printMines();

	}


	private void printMines() {

		for(int r = 0; r < mineGrid.length; r++) {
			for(int c = 0; c < mineGrid[r].length; c++) {
				System.out.print(mineGrid[r][c]);
			}
			System.out.println();
		}



	}


	private int[] createCustomSize() {
		int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows"));
		int cols = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of cols"));
		return new int[]{rows,cols};
	}


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		/*
		 * PLEASE, PLEASE, PLEASE, when you use Java Graphics, have this method be 
		 * the "trigger" for ALL drawing to be done should begin HERE, in this method!!!!!
		 * 
		 * All code needed to redraw this panel from scratch goes here.  JPanels
		 * are double-buffered by default, so don't worry about extra things that are redrawn
		 */
		
		
		g.setColor(Color.blue);
		g.drawString("flags: "+flagsLeft, H_PADDING,TEXT_YC );
		
		g.setColor(Color.MAGENTA);
		g.drawString("time: "+timeElapsed/10+"."+timeElapsed%10, H_PADDING,TEXT_YC+SQ );
		
		//This is how you draw images

		for(int r = 0; r < mineGrid.length; r++)
			for(int c = 0; c< mineGrid[0].length;c++) {
				
				if(clickState[r][c] == 0)
					g.drawImage(this.unClickedSquare, H_PADDING + c*SQ,TOP_PADDING + SQ*r,SQ, SQ,null);
				else if(clickState[r][c] == 2)
					g.drawImage(this.flaggedSquare, H_PADDING + c*SQ,TOP_PADDING + SQ*r,SQ, SQ,null);
				else if(clickState[r][c] == 1) {
					int bombsNearby = numbers[r][c];
					if(bombsNearby==-1) {
						g.setColor(Color.green);
						g.drawString("B" ,H_PADDING + c*SQ+SQ/3,TOP_PADDING + SQ*r+2*SQ/3);
					}
					else if(bombsNearby == 0) {
						g.setColor(Color.red);
						g.fillRect(H_PADDING + c*SQ,TOP_PADDING + SQ*r,SQ, SQ);
					}
					else {
						g.setColor(COLOR_ARR[bombsNearby]);
						g.drawString(""+bombsNearby, H_PADDING + c*SQ+SQ/3,TOP_PADDING + SQ*r+2*SQ/3);
					}
					
				}
			}
		//g.drawImage(this.flaggedSquare, 100,10,null);
		
		//
		//		// you can draw shapes
		//		g.setColor(Color.red);
		//		g.drawLine(200, 30, 80, 200);
		//
		//		// you can make your own colors
		//		g.setColor(new Color(68, 200, 100));
		//		// (x,y) of the upper left hand corner, width = 25, height = 50
		//		g.fillRect(200, 50, 25, 50);

	}

	private void openImages() {

		/*
		 * FOLLOW THE EXAMPLE BELOW FOR OPENING RESOURCES!!!!!!!!!!!!
		 */
		try {
			URL url = getClass().getResource("res/images/unclicked.png");
			unClickedSquare = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening the unclicked.png");
			e.printStackTrace();
		}
		try {
			URL url = getClass().getResource("res/images/flagged.png");
			flaggedSquare = ImageIO.read(url);
		} catch (IOException e) {
			System.out.println("Problem opening the flagged.png");
			e.printStackTrace();
		}
		
	}

}
