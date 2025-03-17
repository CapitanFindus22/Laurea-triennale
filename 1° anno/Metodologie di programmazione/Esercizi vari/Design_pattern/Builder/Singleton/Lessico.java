package Design_pattern.Builder.Singleton;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Lessico {
	
	final static private Path DEFAULT_LEXICON = Paths.get("src","Design_pattern.Singleton","lexicon.txt");
	
	static private Lessico istanza;
	
	private Set<String> lessico = new HashSet<>();
	
	public Lessico()
	{
	}
	
	public Lessico(Path p)
	{
		try {
			Files.lines(p).forEach(lessico::add);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Lessico getIstanza() {
		
		return istanza == null ? new Lessico(DEFAULT_LEXICON) :istanza;
		
	}

	public boolean isWord(String w) { return lessico.contains(w); }
	
	public static void main(String[] args) {

		Lessico l = new Lessico();
		
		System.out.println(l.getIstanza().isWord("asaasaas"));

	}

}
