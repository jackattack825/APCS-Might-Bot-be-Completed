package donjeweled;

import java.io.IOException;
import java.net.URL;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Hall extends Don {
	static Image myImg;
	public Hall() {
		super(0);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if(myImg== null){
			try {
				URL url = getClass().getResource("res/images/hallfixed.jpg");
				myImg = ImageIO.read(url);
				System.out.println(img);
			} catch (IOException e) {
				System.out.println("Problem opening the hallfixed.jpg");
				e.printStackTrace();
			}
		}
		img=myImg;
	}
}