package videostore;

public abstract class Inventory {

	protected abstract Memento saveToMemento();

	protected abstract void restoreFromMemento(Memento m);

	public abstract boolean add(String name, double price, int quantity);

	public abstract boolean sell(String name, int quantity);

	public abstract boolean addCopies(String name, int quantity);

	public abstract boolean changePrice(String name, double price);

	public abstract Movie find(String name);

	public abstract Movie find(int id);

	public double getPrice(String name) {
		Movie movieInstance = find(name);
		if (movieInstance != null)
			return movieInstance.getPrice();
		return -1;

	}

	public double getPrice(int id) {
		Movie movieInstance = find(id);
		if (movieInstance != null)
			return movieInstance.getPrice();
		return -1;
	}

	public int getQuantity(String name) {
		Movie movieInstance = find(name);
		if (movieInstance != null)
			return movieInstance.getQuantity();
		return -1;
	}

	public int getQuantity(int id) {
		Movie movieInstance = find(id);
		if (movieInstance != null)
			return movieInstance.getQuantity();
		return -1;
	}

}
