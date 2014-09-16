package videostore;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcreteInventory extends Inventory {

	private List<Movie> movieList;

	public ConcreteInventory() {
		movieList = new ArrayList<Movie>();
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public boolean add(String name, double price, int quantity) {
		Movie newMovie = new Movie(name, price, quantity);
		movieList.add(newMovie);
		return true;
	}

	public boolean sell(String name, int quantity) {
		Movie movieInstance = find(name);
		if(movieInstance!=null){
		movieInstance.decreaseQuantity(quantity);
		return true;}
		return false;
	}

	public boolean addCopies(String name, int quantity) {
		Movie movieInstance = find(name);
		if(movieInstance!=null){
		movieInstance.increaseQuantity(quantity);
		return true;}
		return false;
	}

	public boolean changePrice(String name, double price) {
		Movie movieInstance = find(name);
		if(movieInstance!=null){
		movieInstance.changePrice(price);
		return true;}
		return false;
	}

	
	public Movie find(String name) {
		Movie movieInstance = null;
		Iterator<Movie> movieIterator = movieList.iterator();
		while (movieIterator.hasNext()) {
			movieInstance = movieIterator.next();
			if (movieInstance.getName().equalsIgnoreCase(name))
				return movieInstance;
		}
		return movieInstance;
	}

	public Movie find(int id) {
		Movie movieInstance = null;
		Iterator<Movie> movieIterator = movieList.iterator();
		while (movieIterator.hasNext()) {
			movieInstance = movieIterator.next();
			if (movieInstance.getName().equals(id))
				return movieInstance;
		}
		return null;
	}

	protected Memento saveToMemento() {

		return new Memento(movieList);
	}

	protected void restoreFromMemento(Memento m) {
		this.movieList = m.restoreMemento();
	}

}
