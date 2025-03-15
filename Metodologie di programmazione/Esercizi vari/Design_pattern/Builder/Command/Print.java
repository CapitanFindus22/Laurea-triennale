package Design_pattern.Builder.Command;

public class Print implements Command {

	private Bottone b;
	
	public Print(Bottone b) {
		
		this.b = b;
		
	}
	
	@Override
	public void esegui() { b.getBottone().setBounds(0, 0, 200, 200);; }

}
