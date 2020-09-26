package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class ProductTest {

	private RestaurantsManager rm;

	public void setupScenary1() throws IOException{
		rm = new RestaurantsManager();
		rm.addRestaurant("Posadero de la esquina", "123456789", "Poncho");
		rm.addProduct("Tequila", "2153", "Directo desde méxico", 20000, "123456789");
		rm.addProduct("Cerveza", "1248", "De trigo", 3500, "123456789");
	}
	
	public void setupScenary2() throws IOException{
		rm = new RestaurantsManager();
		rm.addRestaurant("Posadero de la esquina", "123456789", "Poncho");
		rm.addProduct("Tequila", "2153", "Directo desde méxico", 20000, "123456789");
		rm.addProduct("Cerveza", "2153", "De trigo", 3500, "123456789");
	}

	@Test
	public void testAddProduct_1() throws IOException {
		setupScenary1();
		for (int i = 0; i < rm.restaurants.get(0).products.size(); i++) {
			assertEquals("Products doesn't added to the restaurant product list", "123456789",rm.restaurants.get(0).products.get(i).getRestaurantNit());
		}

	}
	@Test
	public void testAddProduct_2() throws IOException {
		setupScenary2();
		assertEquals("Some product with the same code added", 1,rm.products.size());
	}
}
