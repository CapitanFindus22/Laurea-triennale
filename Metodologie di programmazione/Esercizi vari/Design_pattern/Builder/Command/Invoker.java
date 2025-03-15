package Design_pattern.Builder.Command;

public class Invoker {

	private Command c;
	
	public Invoker(Command c) {
		
		this.c = c;
		
	}
	
	public void setNewCommand(Command c) {
		
		this.c = c;
		
	}
	
	public void execute() {
		
		c.esegui();
		
	}
	
}
