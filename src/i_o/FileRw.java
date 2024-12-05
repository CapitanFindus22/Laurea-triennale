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
 * Contain various functions to read info from text files
 */
public class FileRw {

	//The path to the file containing all the pokemons and their stats
	private static final Path PKMN_PATH = Paths.get("resource","text_file","PokemonList.txt");
	
	//The path to the file containing all the moves and their values
	private static final Path MOVE_PATH = Paths.get("resource","text_file","MoveList.txt");
	
	//The path to the file containing the moves learned by the pokemon on level up
	private static final Path LVL_UP_MOVE_PATH = Paths.get("resource","text_file","LvlUpMoves.txt");
	
	/**
	 * Find a pokemon in the file
	 * 
	 * @param name The name of the pokemon to find
	 * @return A string containing all the info about the pokemon
	 */
	public static String findPokemon(String name) {
		
		String result = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);
			
			String line = "";
			
			while(!line.equals("name:" + name)) {line = br.readLine();}
			
			result += line.replace("name:", "");
			
			for(short i=0;i<11;i++) result += "," + br.readLine();
			
			br.close();
			
		} catch (IOException e) {e.printStackTrace();}
		
		return result;
		
	}
	/**
	 * Find a move in the file
	 * 
	 * @param name The move to find
	 * @return A string containing all the info about the move
	 */
	public static String findMove(String name) {
		
		String result = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(MOVE_PATH);
			
			String line = "";
			
			while(!line.equals(name)) {line = br.readLine();}
			
			result += line;
			
			for(short i=0;i<8;i++) result += "-" + br.readLine();
			
			br.close();
			
		} catch (IOException e) {e.printStackTrace();}
		
		return result;
		
	}
	
	/**
	 * Find all the moves learned by level up of the pokemon
	 * 
	 * @param name The pokemon name
	 * @return A map of the form (level needed,move name)
	 */
	public static TreeMap<Integer,String> getLvlUpMoves(String name) {
		
		TreeMap<Integer,String> result = new TreeMap<>();
		
		try {
			BufferedReader br = Files.newBufferedReader(LVL_UP_MOVE_PATH);
			
			String line = "";
			
			while(!line.contains(name)) line = br.readLine();
			
			for(String l : line.split(",")) if(!l.contains(name)) result.put(Integer.parseInt(l.split(" ")[0]), l.split(" ")[1]);
			
			br.close();
			
		} catch (IOException e) {e.printStackTrace();}
		
		return result;
		
	}
	
	/**
	 * Create a list of all the pokemon in the files
	 * 
	 * @return A set containing all the available pokemon
	 */
	public static TreeSet<String> allPokemon() {
		
		TreeSet<String> result = new TreeSet<>();
		
		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);

			result = br.lines().filter(x -> x.contains("name:")).map(x -> x.replace("name:", "")).collect(Collectors.toCollection(TreeSet::new));
			
			br.close();
			
		} catch (IOException e) {e.printStackTrace();}
		
		return result;
		
	}
	
}
