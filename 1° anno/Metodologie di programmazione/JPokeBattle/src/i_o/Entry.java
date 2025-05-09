package i_o;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;

/**
 * A row used in {@link menu.RecordTable}
 */
public class Entry {

	/**
	 * The path of the record file
	 */
	private final static Path file_path = Paths.get("resource", "text_file", "Record.txt");

	/**
	 * The max number of records to read
	 */
	private final static short MAX_RECORD = 10;

	/**
	 * The player name
	 */
	private String name;

	/**
	 * The number of rounds won
	 */
	private int streak;

	/**
	 * The name of the pokémon used
	 */
	private String pokemon_name;

	/**
	 * Create an entry
	 * 
	 * @param name        the player name
	 * @param streak      the number of rounds won
	 * @param pokemonName the name of the pokémon used
	 */
	public Entry(String name, int streak, String pokemonName) {

		this.name = name;
		this.streak = streak;
		this.pokemon_name = pokemonName;

	}

	/**
	 * Create an entry
	 * 
	 * @param values an array of strings containing: [0] the name, [1] the streak,
	 *               [2] the pokémon name
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

	/**
	 * Fill an array with records from the file
	 * 
	 * @param entries the array to fill
	 */
	public static void fill(Entry[] entries) {

		try {

			BufferedReader br = Files.newBufferedReader(file_path);

			int i = 0;

			// Get up to MAX_RECORD best records from the file and save them in the array
			for (Entry e : br.lines().filter(x -> !x.isEmpty()).map(x -> new Entry(x.split(" ")))
					.sorted(Comparator.comparing(Entry::getStreak).reversed()).limit(MAX_RECORD).toArray(Entry[]::new))

			{
				entries[i++] = e;
			}

			br.close();

		}

		catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	/**
	 * Check if there's a new record 
	 * 
	 * @param roundWon the number of rounds won
	 * @return true if there's a new record
	 */
	public static boolean checkNewRecord(int roundWon) {

		Entry[] entries = new Entry[MAX_RECORD];

		Entry.fill(entries);

		if (isEmpty(entries))
			return true;

		if (entries[0].getStreak() < roundWon)
			return true;

		return false;

	}

	/**
	 * Save a new record in the file
	 * 
	 * @param name        the player name
	 * @param pokemonName the pokémon used
	 * @param roundWon    the number of rounds won
	 */
	public static void saveNewRecord(String name, String pokemonName, int roundWon) {

		String playerName = name;

		// Base name if not passed
		if (name == null || name.isBlank())
			playerName = "AAA";

		Entry[] entries = new Entry[MAX_RECORD];

		Entry.fill(entries);

		int i;

		// Slide the existing array by one
		for (i = MAX_RECORD - 1; i > 0; i--)
			entries[i] = entries[i - 1];

		// Put the new record on top
		entries[0] = new Entry(playerName, roundWon, pokemonName);

		try {

			// Overwrite the file
			@SuppressWarnings("resource")
			BufferedWriter br = Files.newBufferedWriter(file_path, StandardOpenOption.TRUNCATE_EXISTING);

			// Save the records on the file
			for (i = 0; i < MAX_RECORD - 1; i++) {

				if (entries[i] != null)
					br.write(entries[i].toString());

				if (entries[i + 1] != null)
					br.newLine();

			}

			if (entries[i] != null)
				br.write(entries[MAX_RECORD - 1].toString());

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Check if the array passed is empty
	 * 
	 * @param entries the array to control
	 * @return true if it's empty
	 */
	private static boolean isEmpty(Entry[] entries) {
		return entries[0] == null;
	}

	// Getter

	/**
	 * 
	 * {@return the name of the player}
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * {@return the number of rounds won}
	 */
	public int getStreak() {
		return streak;
	}

	/**
	 * 
	 * {@return the used pokémon name}
	 */
	public String getPkmn() {
		return pokemon_name;
	}

}
