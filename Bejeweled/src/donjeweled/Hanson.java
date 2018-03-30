package donjeweled;

import java.io.IOException;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hanson extends Don {
	static Image myImg;
	public Hanson() {
		super(0);
	}

	@Override
	protected void openImage() {
		if(myImg== null){
			// copied from Panel class
			try {
				URL url = getClass().getResource("res/images/hansonfixed.jpg");
				myImg = ImageIO.read(url);
				System.out.println(img);
			} catch (IOException e) {
				System.out.println("Problem opening the hansonfixed.jpg");
				e.printStackTrace();
			}
		}
		img=myImg;
	}
}