package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import videostore.InventoryDecorator;

public class TestRecover {

	

	@Before
	public void addData() {

		InventoryDecorator inventoryDecorator = new InventoryDecorator();
		inventoryDecorator.add("ABC", 30.2, 5);
		inventoryDecorator.addCopies("ABC", 2);
		inventoryDecorator.add("DEF", 15.63, 3);
		inventoryDecorator.add("GHI", 20.32, 2);
		inventoryDecorator.sell("ABC", 3);
	}

	@Test
	public void test() {
		InventoryDecorator decorator = new InventoryDecorator();
		decorator.recover();
		int actualResult = decorator.getQuantity("ABC");
		int expected = 4;
		assertEquals(expected, actualResult);
		
		double actualPrice = decorator.getPrice("DEF");
		double expectedPrice = 15.63;
		assertEquals(expectedPrice, actualPrice,0.1);
	}

}
