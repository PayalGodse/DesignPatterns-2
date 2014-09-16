package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import videostore.ConcreteInventory;
import videostore.InventoryDecorator;

public class TestInventory {

	// Creating a concrete inventory object and testing it without saving memento
	@Test
	public void testConcrete() {
		ConcreteInventory object = new ConcreteInventory();
		object.add("ABC", 30.2, 5);
		object.add("DEF",50.5,4);
		object.sell("ABC", 2);
		
		int expectedResult = 3;
		assertEquals(expectedResult,object.getQuantity("ABC"));
		System.out.println("Id is: "+object.getMovieList().get(0).getUniqueId());
		System.out.println("Id is: "+object.getMovieList().get(1).getUniqueId());
	//	System.out.println("Id is: "+object.getMovieList().get(0).getUniqueId());
	}
	
	@Test
	public void testDecorator() {
		InventoryDecorator object = new InventoryDecorator();
		object.add("ABC", 30.2, 5);
		object.add("DEF",50.5,4);
		
		int actualResult = object.getQuantity("ABC");
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
		
		double actual = object.getPrice("DEF");
		double expected = 50.5;
		assertEquals(expected, actual,0.1);
		
		object.changePrice("DEF", 40.5);
		
		actual = object.getPrice("DEF");
		expected = 40.5;
		assertEquals(expected, actual,0.1);
	}

}
