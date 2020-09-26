package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import model.RestaurantsManager;


class ClientTest {

	private RestaurantsManager rm;

	public void setupScenary1() throws IOException {
		rm = new RestaurantsManager();	
		rm.addClient("Mario", "Casas", "123456789", 3, "987654321", "Cali");
		rm.addClient("Pedro", "Randonga", "1548225645", 4, "3124865794", "Cali");
	}
	@Test
	public void testClientSortedAdded() throws IOException {
		setupScenary1();
		assertEquals("Fail test client", "123456789",rm.clients.get(1).getIdNum());
	}
	@Test
	public void testClientFullName() throws IOException {
		setupScenary1();
		assertEquals("Fail test client full name", "Pedro Randonga",rm.clients.get(0).getFullName());
	}
}
