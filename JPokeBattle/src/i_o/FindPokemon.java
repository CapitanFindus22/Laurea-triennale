package i_o;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindPokemon {

	private static final Path PATH = Paths.get("resource","text_file","PokemonList.txt");
	
	public static String find(String name) {
		
		String result = "";
		
		try {
			BufferedReader br = Files.newBufferedReader(PATH);
			
			String line = "";
			
			while(line != "name") {
				
				line = br.readLine();
				
			}
			
			result += line;
			
			for(short i=0;i<8;i++) result += "," + br.readLine();
			
			result += br.readLine();
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
