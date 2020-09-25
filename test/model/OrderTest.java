package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import org.junit.jupiter.api.Test;

class OrderTest {
	Random random = new Random();
	private RestaurantsManager rm;

	public void setupScenary1() throws IOException{
		rm = new RestaurantsManager();
		rm.addRestaurant("Las delicias de Gallo", "12345", "Gallo");
		rm.addRestaurant("Las delicias de Colombia", "1234", "Camilo");
		rm.addProduct("Arepa", "2153", "Arepa con queso", 1500, "12345");
		rm.addProduct("Morzilla", "1248", "Morzilla por libra", 8500, "12345");
		rm.addProduct("Pollo", "4673", "Pollo por libra", 12000, "1234");
		rm.addProduct("Carne de res", "4675", "Carne de res por libra", 8500, "1234");
		rm.addClient("Andrea", "Corrales", "2154531341", 1, "31254867624", "Cali");
		rm.addClient("Camilo", "Ramirez", "1548225645", 4, "3124865794", "Cali");
		rm.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		rm.orders.get(0).addProducts("4673", 2);
		rm.addOrder((String)new BigInteger(50, random).toString(32), "2154531341", "12345");
		rm.orders.get(1).addProducts("2153", 5);
	}
	@Test
	public void testOrder() throws IOException {
		setupScenary1();
		assertEquals("Fail test in order added", 2, rm.orders.size());

	}
	@Test
	public void testUpdateStatus() throws IOException {
		setupScenary1();
		rm.orders.get(0).updateStatus();
		assertEquals("Fail test in order to update Status", "IN_PROCESS", rm.orders.get(0).getStatus());
	}
}
