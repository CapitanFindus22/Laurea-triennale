package i_o;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class FileRw {

	private static final Path PKMN_PATH = Paths.get("resource","text_file","PokemonList.txt");
	private static final Path MOVE_PATH = Paths.get("resource","text_file","MoveList.txt");
	private static final Path LVL_UP_MOVE_PATH = Paths.get("resource","text_file","LvlUpMoves.txt");
	
	public static String findPokemon(String name) {
		
		String result = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(PKMN_PATH);
			
			String line = "";
			
			while(!line.equals("name:" + name)) {line = br.readLine();}
			
			result += line.replace("name:", "");
			
			for(short i=0;i<11;i++) result += "," + br.readLine();
			
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
			
			while(!line.equals(name)) {line = br.readLine();}
			
			result += line;
			
			for(short i=0;i<8;i++) result += "-" + br.readLine();
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public static TreeMap<Integer,String> getLvlUpMoves(String name) {
		
		TreeMap<Integer,String> result = new TreeMap<>();
		
		try {
			BufferedReader br = Files.newBufferedReader(LVL_UP_MOVE_PATH);
			
			String line = "";
			
			while(!line.equals(name)) {line = br.readLine();}
			
			while(!line.equals("")) {
				
				line = br.readLine();
				if(!line.isBlank() && !line.isEmpty()) result.put(Integer.parseInt(line.split(" ")[0]), line.split(" ")[1]);
				
			}
			
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
