package videostore;

import java.io.Serializable;

import utility.UniqIdentity;

public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private double price;
	private int id;
	private int quantity;

	public Movie(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.id = UniqIdentity.instance().increase();
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getUniqueId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void decreaseQuantity(int quantity) {
		this.quantity = this.quantity - quantity;
	}

	public void increaseQuantity(int quantity) {
		this.quantity = this.quantity + quantity;
	}

	public String toString() {
		return "ID:" + id + "  NAME:" + name + "  PRICE:" + price + "  QUANTITY:" + quantity;
	}

	public void changePrice(double price) {
		if (price > 0)
			this.price = price;

	}

}
