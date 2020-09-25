package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class RestaurantTest {

	private RestaurantsManager rm;
	
	public void setupScenary1() throws IOException {
		rm = new RestaurantsManager();
		rm.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		rm.addRestaurant("Las delicias de Colombia", "12345", "Camilo");
	}
	
	@Test
	public void testSameRestaurantsNit_1() throws IOException {
		setupScenary1();
		assertEquals("There's some restaurant duplicate", 1, rm.restaurants.size());
	}
	@Test
	public void testSameRestaurantsNit_2() throws IOException {
		setupScenary1();
		assertEquals("There's some restaurant duplicate", "Las delicias de Gallo", rm.restaurants.get(0).getName());
	}
}
