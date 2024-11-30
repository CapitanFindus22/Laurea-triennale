package i_o;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

public class SpriteFinder {
	
	public static BufferedImage findFront(String name) {
		
		File file = Paths.get("resource","image","front_sprite",name+".png").toFile();
		try {
			BufferedImage image = ImageIO.read(file);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static BufferedImage findBack(String name) {
		
		File file = Paths.get("resource","image","back_sprite",name+".png").toFile();
		try {
			BufferedImage image = ImageIO.read(file);
			return image;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
}
