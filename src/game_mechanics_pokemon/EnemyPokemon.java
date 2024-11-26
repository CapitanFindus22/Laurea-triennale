package game_mechanics_pokemon;

import java.awt.Image;

import i_o.SpriteFinder;

public class EnemyPokemon extends Pokemon {

	private Image frontSprite;
	
	public EnemyPokemon(String name) {
		
		super(name);
		frontSprite = SpriteFinder.findFront(name);
		frontSprite = frontSprite.getScaledInstance(frontSprite.getWidth(null)*3, frontSprite.getHeight(null)*3, Image.SCALE_DEFAULT);
		
	}
	
	public EnemyPokemon(String name, int level) {
		
		super(name,level);
		frontSprite = SpriteFinder.findFront(name);
		frontSprite = frontSprite.getScaledInstance(frontSprite.getWidth(null)*3, frontSprite.getHeight(null)*3, Image.SCALE_DEFAULT);
		
	}
	
	public Image getFrontSprite() {
		return frontSprite;
	}

	public void setFrontSprite(Image frontSprite) {
		this.frontSprite = frontSprite;
	}

	
}
