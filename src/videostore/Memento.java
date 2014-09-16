package videostore;

import java.io.Serializable;
import java.util.List;

public class Memento implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Movie> movieList;

	protected Memento() {
	}

	protected Memento(List<Movie> list) {
		this.movieList = list;
	}

	protected List<Movie> restoreMemento() {
		return movieList;

	}

}





