package videostore;

public class AddCommand implements ICommand {

	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private int quantity;
	
	public AddCommand(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public boolean execute(Inventory inventory) {
		return inventory.add(name, price, quantity);
	}
}
	