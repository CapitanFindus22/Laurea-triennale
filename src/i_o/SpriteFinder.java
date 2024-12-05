package i_o;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

/**
 * Find the sprites of the pokemon
 */
public class SpriteFinder {
	
	/**
	 * Find the front sprite
	 * 
	 * @param name The name of the pokemon
	 * @return The image of the sprite
	 */
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
	
	/**
	 * Find the back sprite
	 * 
	 * @param name The name of the pokemon
	 * @return The image of the sprite
	 */
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
