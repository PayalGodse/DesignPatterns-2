package videostore;

import java.io.Serializable;

public interface ICommand extends Serializable{

	public boolean execute(Inventory inventory);
}
