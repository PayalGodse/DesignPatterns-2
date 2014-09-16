package videostore;

public class AddCopiesCommand implements ICommand{

	private static final long serialVersionUID = 1L;
	private String name;
	private int quantity;
	
	public AddCopiesCommand(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public boolean execute(Inventory inventory) {
		return inventory.addCopies(name, quantity);
	}
}
