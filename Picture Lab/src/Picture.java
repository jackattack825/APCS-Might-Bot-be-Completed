import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
	///////////////////// constructors //////////////////////////////////

	/**
	 * Constructor that takes no arguments 
	 */
	public Picture ()
	{
		/* not needed but use it to show students the implicit call to super()
		 * child constructors always call a parent constructor 
		 */
		super();  
	}

	/**
	 * Constructor that takes a file name and creates the picture 
	 * @param fileName the name of the file to create the picture from
	 */
	public Picture(String fileName)
	{
		// let the parent class handle this fileName
		super(fileName);
	}

	/**
	 * Constructor that takes the width and height
	 * @param height the height of the desired picture
	 * @param width the width of the desired picture
	 */
	public Picture(int height, int width)
	{
		// let the parent class handle this width and height
		super(width,height);
	}

	/**
	 * Constructor that takes a picture and creates a 
	 * copy of that picture
	 * @param copyPicture the picture to copy
	 */
	public Picture(Picture copyPicture)
	{
		// let the parent class do the copy
		super(copyPicture);
	}

	/**
	 * Constructor that takes a buffered image
	 * @param image the buffered image to use
	 */
	public Picture(BufferedImage image)
	{
		super(image);
	}

	////////////////////// methods ///////////////////////////////////////

	/**
	 * Method to return a string with information about this picture.
	 * @return a string with information about the picture such as fileName,
	 * height and width.
	 */
	public String toString()
	{
		String output = "Picture, filename " + getFileName() + 
				" height " + getHeight() 
				+ " width " + getWidth();
		return output;

	}

	/** Method to set the blue to 0 */
	public void zeroBlue()
	{
		///////////////  I have left this method finished.  This method 
		///////////////  goes to each pixel and zeroes out the blue component
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				pixelObj.setBlue(0);
			}
		}
	}

	/** Method that mirrors the picture around a 
	 * vertical mirror in the center of the picture
	 * from left to right */
	public void mirrorVertical()
	{
		Pixel[][] pixels = this.getPixels2D();
		Pixel leftPixel = null;
		Pixel rightPixel = null;



	}

	/** Mirror just part of a picture of a temple */
	public void mirrorTemple()
	{// This method makes a mirror image of a section of this picture
		// If this picture is of the temple, it mirror images the top.
		// what if this picture is of a different image?
		int mirrorPoint = 276;
		Pixel leftPixel = null;
		Pixel rightPixel = null;
		int count = 0;
		Pixel[][] pixels = this.getPixels2D();

		// loop through the rows
		for (int row = 27; row < 97; row++)
		{
			// loop from 13 to just before the mirror point
			for (int col = 13; col < mirrorPoint; col++)
			{

				leftPixel = pixels[row][col];      
				rightPixel = pixels[row]                       
						[mirrorPoint - col + mirrorPoint];
				rightPixel.setColor(leftPixel.getColor());
			}
		}
	}

	/** copy from the passed fromPic to the
	 * specified startRow and startCol in the
	 * current picture
	 * @param fromPic the picture to copy from
	 * @param startRow the start row to copy to
	 * @param startCol the start col to copy to
	 */
	public void copy(Picture fromPic, 
			int startRow, int startCol)
	{
		Pixel fromPixel = null;
		Pixel toPixel = null;
		Pixel[][] toPixels = this.getPixels2D();
		Pixel[][] fromPixels = fromPic.getPixels2D();
		for (int fromRow = 0, toRow = startRow; 
				fromRow < fromPixels.length &&
				toRow < toPixels.length; 
				fromRow++, toRow++)
		{
			for (int fromCol = 0, toCol = startCol; 
					fromCol < fromPixels[0].length &&
					toCol < toPixels[0].length;  
					fromCol++, toCol++)
			{
				fromPixel = fromPixels[fromRow][fromCol];
				toPixel = toPixels[toRow][toCol];
				toPixel.setColor(fromPixel.getColor());
			}
		}   
	}

	/** Method to create a collage of several pictures */
	public void createCollage()
	{
		Picture flower1 = new Picture("flower1.jpg");
		Picture flower2 = new Picture("flower2.jpg");
		this.copy(flower1,0,0);
		this.copy(flower2,100,0);
		this.copy(flower1,200,0);
		Picture flowerNoBlue = new Picture(flower2);
		flowerNoBlue.zeroBlue();
		this.copy(flowerNoBlue,300,0);
		this.copy(flower1,400,0);
		this.copy(flower2,500,0);
		this.mirrorVertical();
		this.write("collage.jpg");
	}


	/** Method to show large changes in color 
	 * This method traverses this picture and changes to pixels to 
	 * black and white, depending on the color to each pixel's right.
	 * If the color change is large, then the pixel on the left is set to 
	 * black, otherwise, if the color is close, then the pixel is set to 
	 * white. The result is an image with black pixels where it detected 
	 * a significant change in color.
	 * 
	 * @param edgeDist the distance for finding edges
	 */
	public void edgeDetection(int edgeDist)
	{
		Pixel leftPixel = null;// this pixel will always be the one to 
		// the left of rightPixel.  If this Pixel
		// is far enough away (based on edgeDist), then
		// leftPixel is set to Color black, else, white

		Pixel rightPixel = null;// this Pixel doesn't change value, it is just
		// used as a reference for comparing with leftPixel

		Pixel[][] pixels = this.getPixels2D();// gets the 2D array of Pixel
		// Big hint, the Pixel class has a method called colorDistance(Color) which
		// returns the distance the input Color is from this Pixel's Color
		for (int i=0; i<pixels.length-1;i++){
			for (int x=0; x<pixels[i].length-1;x++){
				if(pixels[i][x].colorDistance(pixels[i][x+1].getColor()) > edgeDist){

					pixels[i][x].setBlue(0);
					pixels[i][x].setRed(0);
					pixels[i][x].setGreen(0);
				}
				else {
					pixels[i][x].setRed(255);
					pixels[i][x].setGreen(255);
					pixels[i][x].setBlue(255);
				}
			}
		}

		/*for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{
				if(> edgeDist){

				pixelObj.setBlue(0);
				pixelObj.setRed(0);
				pixelObj.setGreen(0);
				}
				else {
					pixelObj.setRed(255);
					pixelObj.setGreen(255);
					pixelObj.setBlue(255);
				}
			}

		}
		 */
	}




	public static void main(String[] args) 
	{
		Picture beach = new Picture("beach.jpg");
		beach.explore();
		beach.zeroBlue();
		beach.explore();
	}


	public void onlyBlue(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{

				//	        pixelObj.setRed(0);
				//	        pixelObj.setGreen(0);
				pixelObj.setBlue(255);
			}

		}

	}

	public void onlyGreen(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{


				pixelObj.setGreen(255);
			}

		}

	}
	public void onlyRed(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{


				pixelObj.setRed(255);
			}

		}

	}
	public void negate(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{


				pixelObj.setRed(255-pixelObj.getRed());
				pixelObj.setBlue(255-pixelObj.getBlue());
				pixelObj.setGreen(255-pixelObj.getGreen());
			}

		}
	}
	public void grayScale(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray)
			{

				int x= (pixelObj.getRed()+pixelObj.getGreen()+pixelObj.getBlue())/3;
				pixelObj.setRed(x);
				pixelObj.setBlue(x);
				pixelObj.setGreen(x);

				/* doesn't work, not sure why
				 * pixelObj.setRed((int)pixelObj.getAverage());
				pixelObj.setBlue((int)pixelObj.getAverage());
				pixelObj.setGreen((int)pixelObj.getAverage());
				 */


			}

		}
	}
	public void fixUnderwater(){
		Pixel[][] pixels = this.getPixels2D();
		for (Pixel[] rowArray : pixels)
		{
			for (Pixel p : rowArray){
				p.setGreen(p.getGreen()*75/100);
				p.setBlue(p.getBlue()*80/100);
				int r = p.getRed()*3;
				if(r > 250 )r = 250;
				p.setRed(r);
				//if(pixelObj.getBlue()>150 && pixelObj.getGreen()>150){
				//pixelObj.setAlpha(50);
				//pixelObj.setRed(255-pixelObj.getRed());
				//}

			}

		}
		/*		for (Pixel[] rowArray : pixels)
		{
			for (Pixel pixelObj : rowArray){
				if(pixelObj.getRed()>200)
					pixelObj.setRed(pixelObj.getRed()+5);
				else
					pixelObj.setRed(pixelObj.getRed()+20);
				pixelObj.setBlue(pixelObj.getBlue()-30);
				pixelObj.setGreen(pixelObj.getGreen()-30);
			}

		}
	}*/
	}
	public void chromaKey(Picture mesg, int row, int col){
		
		
		Pixel[][] pixels = this.getPixels2D();
		Pixel[][] messag = mesg.getPixels2D();
		for (int i=0; i<messag.length && row+i < pixels.length;i++){
			for (int x=0; x<messag[i].length&& col+x<pixels[i].length;x++){
//		for (int i=0; i<messag.length ;i++){
//			for (int x=0; x<messag[i].length;x++){
				if (messag[i][x].getGreen()>200 && messag[i][x].getBlue()<20 && messag[i][x].getRed()<20){
//					pixels[i][x].setBlue(messag[i][x].getBlue());
//					pixels[i][x].setRed(messag[i][x].getRed());
//					
					System.out.println("NOT Changing "+(row+i)+" , "+(col+x));
				}
				else{
					System.out.println("Changing "+(row+i)+" , "+(col+x));
					pixels[row+i][col+x].setColor(messag[i][x].getColor());
				}
		
	}
		}
	}
}

	



/*	public void edgeDetection2(int edgeDist)
	{

		Pixel[][] pixels = this.getPixels2D();// gets the 2D array of Pixel

		for (int i=0; i<pixels.length-1;i++){
			for (int x=0; x<pixels[i].length-1;x++){
				if(pixels[i][x].colorDistance(pixels[i][x+1].getColor()) > edgeDist){

					pixels[i][x].setBlue(0);
					pixels[i][x].setRed(0);
					pixels[i][x].setGreen(0);
					}
					else {
						pixels[i][x].setRed(255);
						pixels[i][x].setGreen(255);
						pixels[i][x].setBlue(255);
					}
			}
		}
}
}
 */
// this } is the end of class Picture, put all new methods before this
