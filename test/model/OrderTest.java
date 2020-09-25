package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;

class OrderTest {
	Random random = new Random();
	private RestaurantsManager restaurantsManager = new RestaurantsManager();
	public void setupScenary1() throws IOException{

		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		restaurantsManager.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		restaurantsManager.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		restaurantsManager.addProduct("Pollo", "4673", "Pollo por libra", 12000, "1234");
		restaurantsManager.addProduct("Carne de res", "4675", "Carne de res por libra", 8500, "1234");
		restaurantsManager.addClient("Andrea", "Corrales", "2154531341", 1, "31254867624", "Cali");
		restaurantsManager.addClient("Camilo", "Ramirez", "1548225645", 4, "3124865794", "Cali");
		restaurantsManager.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		restaurantsManager.orders.get(0).addProducts("4673", 2);
		restaurantsManager.addOrder((String)new BigInteger(50, random).toString(32), "1548225645", "12345");
		restaurantsManager.orders.get(1).addProducts("1248", 5);
	}
	public void setupScenary2() throws IOException{

		restaurantsManager.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		restaurantsManager.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		restaurantsManager.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		restaurantsManager.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		restaurantsManager.addProduct("Pollo", "4673", "Pollo por libra", 12000, "1234");
		restaurantsManager.addProduct("Carne de res", "4675", "Carne de res por libra", 8500, "1234");
		restaurantsManager.addClient("Andrea", "Corrales", "2154531341", 1, "31254867624", "Cali");
		restaurantsManager.addClient("Camilo", "Ramirez", "1548225645", 4, "3124865794", "Cali");
		restaurantsManager.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		restaurantsManager.orders.get(0).addProducts("4673", 2);
		restaurantsManager.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		restaurantsManager.orders.get(1).addProducts("2153", 5);
		restaurantsManager.orders.get(0).addProducts("4673", 3);
	}
	@Test
	public void testOrderAdded_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test in order added", 2, restaurantsManager.orders.size());

	}
	@Test
	public void testOrderSearchProductCode_1() throws IOException {
		setupScenary2();
		assertEquals("Fail test in order to search", 1, restaurantsManager.orders.get(1).searchProductInList("4673"));
	}
}
