package game_mechanics_pokemon;

import java.awt.Image;

import i_o.SpriteFinder;

public class PlayerPokemon extends Pokemon{

	private Image backSprite;
	
	public PlayerPokemon(String name) {
		
		super(name);
		backSprite = SpriteFinder.findBack(name);
		backSprite = backSprite.getScaledInstance(backSprite.getWidth(null)*2, backSprite.getHeight(null)*2, Image.SCALE_DEFAULT);
		
	}

	public Image getBackSprite() {
		return backSprite;
	}

	public void setBackSprite(Image backSprite) {
		this.backSprite = backSprite;
	}
	
}
