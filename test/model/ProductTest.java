package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.jupiter.api.Test;


class ProductTest {

	private RestaurantsManager restaurantsManager = new RestaurantsManager();

	public void setupScenary1() throws IOException{

		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		restaurantsManager.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		restaurantsManager.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		restaurantsManager.addProduct("Pollo", "4673", "Pollo por libra", 12000, "1234");
		restaurantsManager.addProduct("Carne de res", "4675", "Carne de res por libra", 8500, "1234");
	}
	public void setupScenary2() throws IOException{

		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		restaurantsManager.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		restaurantsManager.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		restaurantsManager.addProduct("Pollo", "4673", "Pollo por libra", 12000, "1234");
		restaurantsManager.addProduct("Carne de res", "4673", "Carne de res por libra", 8500, "1234");
		restaurantsManager.addProduct("Tamal", "3515", "Tamal de pipian", 5000, "12345");
	}
	
	@Test
	public void testProductAddedForEachRestaurant_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test product added to restaurant", "1234",restaurantsManager.getProducts().get(3).getRestaurantNit());

	}
	@Test
	public void testProductSameCode_2() throws IOException {
		setupScenary2();
		assertEquals("Fail test product with same code added to restaurant", 4,restaurantsManager.getProducts().size());
	}
}
