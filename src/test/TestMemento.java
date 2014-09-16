package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import videostore.ConcreteInventory;
import videostore.InventoryDecorator;

public class TestMemento {

	@Test
	public void testMemento() {

		ConcreteInventory originator = new ConcreteInventory();
		originator.add("ABC", 30.2, 6);
		originator.add("DEF", 50.5, 4);
		
		InventoryDecorator decorator = new InventoryDecorator(originator);
		decorator.save();
		originator.add("XYZ", 25.3, 3);
	
		decorator.getMemento();

		int actualResult = decorator.getQuantity("ABC");
		int expected = 6;
		assertEquals(expected, actualResult);
		
		actualResult = decorator.getQuantity("DEF");
		expected = 4;
		assertEquals(expected, actualResult);
		
		double actualPrice = decorator.getPrice("DEF");
		double expectedPrice = 50.5;
		assertEquals(expectedPrice, actualPrice,0.1);
		System.out.println("Id iss: "+originator.getMovieList().get(0).getUniqueId());
		System.out.println("Id iss: "+originator.getMovieList().get(1).getUniqueId());
		
	}

}
