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
		rm.addRestaurant("Posadero de la esquina", "123456789", "Poncho");
		rm.addProduct("Tequila", "2153", "Directo desde méxico", 20000, "123456789");
		rm.addClient("Mario", "Casas", "123456789", 3, "987654321", "Cali");
		rm.addOrder((String)new BigInteger(50, random).toString(32), "123456789", "123456789");
		rm.orders.get(0).addProducts("2153", 2);
	}
	@Test
	public void testOrder() throws IOException {
		setupScenary1();
		assertEquals("Fail test in order added", 1, rm.orders.size());

	}
	@Test
	public void testUpdateStatus() throws IOException {
		setupScenary1();
		rm.orders.get(0).updateStatus();
		assertEquals("Fail test in order to update Status", "IN_PROCESS", rm.orders.get(0).getStatus());
	}
}
