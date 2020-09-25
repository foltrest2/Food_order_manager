package model;

import static org.junit.Assert.assertEquals;
import java.io.IOException;


import org.junit.jupiter.api.Test;

import model.RestaurantsManager;


class ClientTest {
	
	private RestaurantsManager rm;
	
	public void setupScenary1() {
		rm = new RestaurantsManager();
		try {
			rm.addClient("Andrea", "Corrales", "2154531341", 1, "31254867624", "Cali");
			rm.addClient("Camilo", "Ramirez", "1548225645", 4, "3124865794", "Cali");
		} catch (IOException e) {
			System.err.println("Something failed");
		}	
	}
	@Test
	public void testClientSortedAdded_1() throws IOException {
		setupScenary1();
		assertEquals("Fail test client", "2154531341",rm.clients.get(1).getIdNum());
	}
	@Test
	public void testClientFullName_2() throws IOException {
		setupScenary1();
		assertEquals("Fail test client full name", "Camilo Ramirez",rm.clients.get(0).getFullName());
	}
}
