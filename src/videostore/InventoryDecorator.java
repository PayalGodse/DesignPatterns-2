package videostore;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class InventoryDecorator extends Inventory {
	private Inventory inventoryObject;
	private static int countCommands = 3;
	// max number of command can be customized,for testing purpose taken 3
	private ICommand command;

	public InventoryDecorator() {
		this.inventoryObject = new ConcreteInventory();
		command = new NoCommand();
	}

	public InventoryDecorator(Inventory inventoryObject) {
		this.inventoryObject = inventoryObject;
		command = new NoCommand();
	}

	public Inventory getInventory() {
		return inventoryObject;
	}

	public boolean add(String name, double price, int quantity) {
		checkCount();
		command = new AddCommand(name, price, quantity);
		return performSaveCommand();
	}

	public boolean sell(String name, int quantity) {
		checkCount();
		command = new SellCommand(name, quantity);
		return performSaveCommand();
	}

	public boolean addCopies(String name, int quantity) {
		checkCount();
		command = new AddCopiesCommand(name, quantity);                    
		return performSaveCommand();
	}

	public boolean changePrice(String name, double price) {
		checkCount();
		command = new ChangePriceCommand(name, price);
		return performSaveCommand();
	}

	public Movie find(String name) {
		return inventoryObject.find(name);
	}

	public Movie find(int id) {
		return inventoryObject.find(id);
	}

	// executes the command and saves it
	private boolean performSaveCommand() {
		boolean isSuccess = command.execute(inventoryObject);
		FileOutputStream fileOut;
		try {
			if (countCommands == 3) {
				fileOut = new FileOutputStream("../command.ser");
			} else {
				fileOut = new FileOutputStream("../command.ser", true);
			}
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(command);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			return false;
		}
		return isSuccess;
	}

	// saves memento if commads file becomes big 
	private void checkCount() {
		if (countCommands == 0) {
			save();
			resetFile();
			countCommands = 3;
		} else
			countCommands--;
	}

	// restore by getting memento and then performing commands on it
	public void recover() {
		getMemento();
		List<ICommand> commands = new ArrayList<ICommand>();
		commands = getRequest();
		for (ICommand operations : commands) {
			operations.execute(inventoryObject);
		}
	}

	// saving memento, if file is successfully written renaming the file else keep the old file
	public void save() {
		try {
			FileOutputStream fileOut = new FileOutputStream("../temp.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(inventoryObject.saveToMemento());
			out.close();
			fileOut.close();
			File oldFile = new File("../inventory.ser");
			oldFile.delete();
			File file = new File("../temp.ser");
			file.renameTo(new File("../inventory.ser"));
		} catch (IOException i) {
			i.printStackTrace();
		}

	}

	//reads memento from file
	public Inventory getMemento() {
		try {
			FileInputStream fileIn = new FileInputStream("../inventory.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			inventoryObject.restoreFromMemento((Memento) in.readObject());
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		}

		return inventoryObject;
	}

	// returns list of commands read from file
	private List<ICommand> getRequest() {
		List<ICommand> commands = new ArrayList<ICommand>();
		ICommand command = new NoCommand();
		try {
			FileInputStream fileIn = new FileInputStream("../command.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);

			boolean fileEnded = false;
			Object object;
			command = (ICommand) in.readObject();

			while (!fileEnded) {
				commands.add(command);
				in = new ObjectInputStream(fileIn);
				if (!((object = in.readObject()) instanceof ICommand)) {
					fileEnded = true;
				} else
					command = (ICommand) object;
			}
			in.close();
			fileIn.close();
		} catch (EOFException e) {
			return commands;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return commands;
 
	}

	// clear the contents of command file
	private void resetFile() {
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("../command.ser");
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected Memento saveToMemento() {
		return inventoryObject.saveToMemento();
	}

	@Override
	protected void restoreFromMemento(Memento m) {
		inventoryObject.restoreFromMemento(m);
	}

}
