package model;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import model.RestaurantsManager;

class RestaurantsManagerTest {

	private RestaurantsManager rm;

	public void setupScenary1() throws IOException {
		rm = new RestaurantsManager();
		rm.addRestaurant("Posadero de la esquina", "123456789", "Poncho");
	}

	@Test
	void testAddRestaurant() throws IOException {
		setupScenary1();
		rm.addRestaurant("Posadero de la esquina", "123456789", "Poncho");
		assertNotNull("Add method doesn't works", rm.restaurants);

	}

	@Test
	void testUpdateRestaurantName() throws IOException {
		setupScenary1();
		for (int i = 0; i < rm.restaurants.size(); i++) {
			if (rm.restaurants.get(i).getNit().equals("9182340981")) {
				rm.updateRestaurantName(rm.restaurants.get(i).getNit(), "Comidas rápidas Juan");
				assertEquals("Comidas rápidas Juan", rm.restaurants.get(i).getName(), "The name doesn't change");
			}
		}
	}

}
