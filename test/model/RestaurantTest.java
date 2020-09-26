package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class RestaurantTest {

	private RestaurantsManager rm;
	
	public void setupScenary1() throws IOException {
		rm = new RestaurantsManager();
		rm.addRestaurant("Posadero de la esquina", "123456789", "Poncho");
		rm.addRestaurant("Manciladopidira", "123456789", "María");
	}
	
	@Test
	public void testSameRestaurantsNit_1() throws IOException {
		setupScenary1();
		assertEquals("There's some restaurant duplicate", 1, rm.restaurants.size());
	}
	@Test
	public void testSameRestaurantsNit_2() throws IOException {
		setupScenary1();
		assertEquals("There's some restaurant duplicate", "Posadero de la esquina", rm.restaurants.get(0).getName());
	}
}
