package i_o;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 * Encapsulate a line used in the leaderboard
 */
public class Entry {

	private final static Path file_path = Paths.get("resource","text_file","Record.txt");
	
	private String name;
	private int streak;
	private String pokemon_name;
	
	/*
	 * Set all the attributes
	 * 
	 * @argument name The name of the player
	 * @argument streak The number of rounds won
	 * @argument pokemon_name The name of the pokemon used
	 */
	public Entry(String name, int streak, String pokemon_name) {
		
		this.name = name;
		this.streak = streak;
		this.pokemon_name = pokemon_name;
		
	}

	/*
	 * Set all the attributes
	 * 
	 * @argument values An array containing the 3 value in order: player name, rounds won, pokemon used
	 */
	public Entry(String[] values) {
		
		this.name = values[0];
		this.streak = Integer.parseInt(values[1]);
		this.pokemon_name = values[2];
		
	}
	
	/*
	 * Fill the entries array with the lines from a file
	 * 
	 * @argument path The path of the file
	 * @argument record The array to fill
	 */
	public static void fill(Entry[] entries) {
		
		try {
			BufferedReader br = Files.newBufferedReader(file_path);

			String line = "";
			
			for(int i=0; i < entries.length; i++) {
				
				line = br.readLine();
				
				if(line != null) {
					
					entries[i] = new Entry(line.split(" "));
					
				}
				
				else {
					
					break;
					
				}
				
			}
			
			br.close();
			
		} 
		
		catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	//Getter
	
	/*
	 * Return the name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Return the streak of victories
	 */
	public int getStreak() {
		return streak;
	}
	
	/*
	 * Return the pokemon used
	 */
	public String getPkmn() {
		return pokemon_name;
	}
	
}
