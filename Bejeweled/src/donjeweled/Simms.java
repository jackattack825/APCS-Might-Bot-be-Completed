package donjeweled;

import java.io.IOException;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;

public class Simms extends Don {
	static Image myImg;
	public Simms() {
		super(0);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if(myImg== null){
			try {
				URL url = getClass().getResource("res/images/simmsfixed.jpg");
				myImg = ImageIO.read(url);
				System.out.println("Just opened: "+myImg);
			} catch (IOException e) {
				System.out.println("Problem opening the simmsfixed.jpg");
				e.printStackTrace();
			}
		}
		img=myImg;
	}
}
