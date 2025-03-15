package Stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FILE {

	public static void main(String[] args) throws IOException {

		BufferedReader b = Files.newBufferedReader(Paths.get("src","Stream","file.txt"));
		
		//Crea una mappa che associa le righe alle loro occorrenze
		Map<String,Long> m = b.lines().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		b.close();
		
	}

}
