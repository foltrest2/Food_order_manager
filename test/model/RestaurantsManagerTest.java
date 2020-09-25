package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.RestaurantsManager;

class RestaurantsManagerTest {

	private RestaurantsManager rm;
	
	public void setupScenary1() {
		rm = new RestaurantsManager();
	}
	
	public void setupScenary2() {
		rm = new RestaurantsManager();
		try {
			rm.addRestaurant("Las delicias de Juan", "9182340981", "Juan");
		} catch (IOException e) {
			System.err.println("Something doesn't works");
		}
	}
	
	@Test
	void testUpdateRestaurantName() {
		setupScenary2();
		for (int i = 0; i < rm.restaurants.size(); i++) {
			if (rm.restaurants.get(i).getNit().equals("9182340981")) {
				rm.updateRestaurantName(rm.restaurants.get(i).getNit(), "Comidas rápidas Juan");
				assertEquals("Comidas rápidas Juan", rm.restaurants.get(i).getName(), "The name doesn't change");
			}
		}
	}
	
	@Test
	void testAddRestaurant() {
		setupScenary1();
		try {
			rm.addRestaurant("Las delicias de Juan", "9182340981", "Juan");
			assertNotNull("Add method doesn't works", rm.restaurants);
		} catch (IOException e) {
			System.err.println("Something doesn't works");
		}
	}
	
	

}
