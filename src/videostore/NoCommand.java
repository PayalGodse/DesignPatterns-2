package videostore;

public class NoCommand implements ICommand{

	private static final long serialVersionUID = 1L;
	
	public NoCommand() {
	}
	public boolean execute(Inventory inventory) {
		return true;
	}
}
