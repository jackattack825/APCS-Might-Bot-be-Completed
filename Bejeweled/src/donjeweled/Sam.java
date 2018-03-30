package donjeweled;

import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import java.awt.Image;
public class Sam extends Don {
	static Image myImg;
	public Sam() {
		super(0);
	}

	@Override
	protected void openImage() {
		// copied from Panel class
		if(myImg== null){
			try {
				URL url = getClass().getResource("res/images/samfixed.jpg");
				myImg = ImageIO.read(url);
				System.out.println(img);
			} catch (IOException e) {
				System.out.println("Problem opening the samfixed.jpg");
				e.printStackTrace();
			}
		}
		img=myImg;
	}
}