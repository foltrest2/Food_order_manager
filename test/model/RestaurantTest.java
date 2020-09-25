package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class RestaurantTest {

	private RestaurantsManager restaurantsManager = new RestaurantsManager();

	public void setupScenary1() throws IOException {
		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "12345", "Camilo");
	}
	
	@Test
	public void testSameRestaurantsNit_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test restaurants duplicate", 1, restaurantsManager.getRestaurants().size());
	}
	@Test
	public void testSameRestaurantsNit_2() throws IOException {
		setupScenary1();
		assertEquals("Fail test restaurants duplicate", "Las delicias de Gallo", restaurantsManager.getRestaurants().get(0).getName());
	}
}
