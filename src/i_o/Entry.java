package i_o;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * Encapsulate a line used in the leaderboard
 */
public class Entry {

	private final static Path file_path = Paths.get("resource","text_file","Record.txt");
	
	private final static short MAX_RECORD = 10;
	
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
	
	@Override
	public String toString() {
		
		return getName() + " " + getStreak() + " " + getPkmn();
		
	}
	
	/*
	 * Fill the entries array with the lines from a file
	 * 
	 * @argument record The array to fill
	 */
	public static void fill(Entry[] entries) {
		
		try {
			
			BufferedReader br = Files.newBufferedReader(file_path);
			
			String line = "";
			
			int i = 0;
			
			while(i < MAX_RECORD) {
				
				line = br.readLine();
				
				if(line != null && !line.isBlank() && !line.isEmpty()) {
					
					entries[i] = new Entry(line.split(" "));
					
				}
				
				i++;
				
			}
			
			br.close();
			
		} 
		
		catch (IOException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public static boolean checkNewRecord(int roundWon) {
		
		Entry[] entries = new Entry[MAX_RECORD];
		
		Entry.fill(entries);
		
		if(isEmpty(entries)) return true;
		
		return (entries[0].getStreak()<roundWon)?true:false;
		
	}
	
	public static void saveNewRecord(String name, String pokemonName, int roundWon) {
		
		String playerName = name;
		
		if(name == null || name.isBlank() || name.isEmpty()) playerName = "Player";
		
		Entry[] entries = new Entry[MAX_RECORD];
		
		Entry.fill(entries);
		
		int notNull = 0;
		
		while(entries[notNull] != null && notNull < MAX_RECORD-1) notNull++;
	
		for(;notNull>0;notNull--) entries[notNull] = entries[notNull-1];
		
		entries[0] = new Entry(playerName,roundWon,pokemonName);	
			
		try {
			
			@SuppressWarnings("resource")
			BufferedWriter br = Files.newBufferedWriter(file_path,StandardOpenOption.TRUNCATE_EXISTING);
			
			for(notNull=0;notNull<MAX_RECORD-1;notNull++) {
				
				if(entries[notNull] != null) {
					
					br.write(entries[notNull].toString());
					br.newLine();
					
				}
				
			}
			
			if(entries[notNull] != null) {
			
				br.write(entries[MAX_RECORD-1].toString());
			
			}
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	private static boolean isEmpty(Entry[] entries) {
		
		return (entries[0]==null)?true:false;
		
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
