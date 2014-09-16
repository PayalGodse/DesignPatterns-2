package videostore;

public class ChangePriceCommand implements ICommand{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	
	public ChangePriceCommand(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public boolean execute(Inventory inventory) {
		return inventory.changePrice(name, price);
	}

}
