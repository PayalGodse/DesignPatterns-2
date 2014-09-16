package videostore;

public class SellCommand implements ICommand{

	private static final long serialVersionUID = 1L;
	private String name;
	private int quantity;
	
	public SellCommand(String name, int quantity) {
		this.name = name;
		this.quantity = quantity;
	}

	public boolean execute(Inventory inventory) {
		return inventory.sell(name,quantity);
	}
}
