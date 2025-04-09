package i_o;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Contain various functions to read info from the text files
 */
public class FileRw {

	/**
	 * The path to the file containing all the pokémons and their stats
	 */
	private static final Path PKMN_PATH = Paths.get("resource", "text_file", "PokemonList.txt");

	/**
	 * The path to the file containing all the moves and their values
	 */
	private static final Path MOVE_PATH = Paths.get("resource", "text_file", "MoveList.txt");

	/**
	 * The path to the file containing the moves learned by the pokémon on level up
	 */
	private static final Path LVL_UP_MOVE_PATH = Paths.get("resource", "text_file", "LvlUpMoves.txt");

	/**
	 * Find a pokémon in the file
	 * 
	 * @param name the name of the pokémon to find
	 * @return a string containing all the info about the pokémon
	 */
	public static String findPokemon(String name) {

		String result = "";

		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);

			String line = "";

			while (!line.equals("name:" + name)) {
				line = br.readLine();
			}

			result += line.replace("name:", "");

			for (short i = 0; i < 11; i++)
				result += "," + br.readLine();

			br.close();

		} catch (IOException e) {

			result = "MissingNo.,Volante,Normale,Pistolacqua,Legatutto,178,19,11,23,23,0,0";
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Find a move in the file
	 * 
	 * @param name the move to find
	 * @return a string containing all the info about the move
	 */
	public static String findMove(String name) {

		String result = "";

		try {
			BufferedReader br = Files.newBufferedReader(MOVE_PATH);

			String line = "";

			while (!line.equals(name)) {
				line = br.readLine();
			}

			result += line;

			for (short i = 0; i < 8; i++)
				result += "," + br.readLine();

			br.close();

		} catch (IOException e) {
			result = "Teletrasporto,Psico,,100,20,SelfStats,Att,0";
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Find all the moves learned by level up by a pokémon
	 * 
	 * @param name the pokémon name
	 * @return a map of the form (level_needed, move_name)
	 */
	public static TreeMap<Integer, String> getLvlUpMoves(String name) {

		TreeMap<Integer, String> result = new TreeMap<>();

		try {
			BufferedReader br = Files.newBufferedReader(LVL_UP_MOVE_PATH);

			String line = "";

			while (!line.contains(name))
				line = br.readLine();

			for (String l : line.split(","))
				if (!l.contains(name))
					result.put(Integer.parseInt(l.trim().split(" ")[0]), l.trim().split(" ")[1]);

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}

	/**
	 * Create a list of all the pokémon present in the files
	 * 	
	 * @return a set containing all the available pokémon
	 */
	public static TreeSet<String> allPokemon() {

		TreeSet<String> result = new TreeSet<>();

		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);

			result = br.lines().filter(x -> x.contains("name:")).map(x -> x.replace("name:", ""))
					.collect(Collectors.toCollection(TreeSet::new));

			br.close();

		} catch (IOException e) {
			result.add("Nessuno");
			e.printStackTrace();
		}

		return result;

	}

}
