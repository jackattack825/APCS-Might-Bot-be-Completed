package donjeweled;

import java.io.IOException;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class DrDre extends Don {
	static Image myImg;
	public DrDre() {
		super(0);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if(myImg== null){
			try {
				URL url = getClass().getResource("res/images/drdrefixed.jpg");
				myImg = ImageIO.read(url);
				System.out.println(img);
			} catch (IOException e) {
				System.out.println("Problem opening the drdrefixed.jpg");
				e.printStackTrace();
			}
		}
		img=myImg;
	}
}