package i_o;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FileReader {

	private static final Path PKMN_PATH = Paths.get("resource","text_file","PokemonList.txt");
	private static final Path MOVE_PATH = Paths.get("resource","text_file","MoveList.txt");
	
	public static String findPokemon(String name) {
		
		String result = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);
			
			String line = "";
			
			while(!line.equals("name:" + name)) {
				
				line = br.readLine();
				
			}
			
			result += line.replace("name:", "");
			
			for(short i=0;i<10;i++) result += "," + br.readLine();
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static String findMove(String name) {
		
		String result = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(MOVE_PATH);
			
			String line = "";
			
			while(!line.equals(name)) {
				
				line = br.readLine();
				
			}
			
			result += line;
			
			for(short i=0;i<5;i++) result += "," + br.readLine();
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	
	public static TreeSet<String> allPokemon() {
		
		TreeSet<String> result = new TreeSet<>();
		
		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);

			result = br.lines().filter(x -> x.contains("name:")).map(x -> x.replace("name:", "")).collect(Collectors.toCollection(TreeSet::new));
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
