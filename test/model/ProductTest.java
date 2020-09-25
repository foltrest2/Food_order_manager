package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class ProductTest {

	private RestaurantsManager rm;

	public void setupScenary1() throws IOException{
		rm = new RestaurantsManager();
		rm.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		rm.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		rm.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		rm.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		rm.addProduct("Pollo", "4673", "Pollo por libra", 12000, "1234");
		rm.addProduct("Carne de res", "4675", "Carne de res por libra", 8500, "1234");
	}
	
	public void setupScenary2() throws IOException{
		rm = new RestaurantsManager();
		rm.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		rm.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		rm.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		rm.addProduct("Pollo", "4673", "Pollo por libra", 12000, "12345");
		rm.addProduct("Carne de res", "4673", "Carne de res por libra", 8500, "12345");
	}

	@Test
	public void testAddProduct_1() throws IOException {
		setupScenary1();
		for (int i = 0; i < rm.restaurants.get(0).products.size(); i++) {
			assertEquals("Products doesn't added to the restaurant product list", "12345",rm.restaurants.get(0).products.get(i).getRestaurantNit());
		}

	}
	@Test
	public void testAddProduct_2() throws IOException {
		setupScenary2();
		assertEquals("Some product with the same code added", 3,rm.products.size());
	}
}
