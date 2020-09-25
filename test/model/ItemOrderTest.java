package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.RestaurantsManager;

class ItemOrderTest {

	RestaurantsManager rm;
	public void setupScenary1() {
		rm = new RestaurantsManager();
		rm.addOrder("l1hjqmus1f", "1654898765", "3151598465");
		int position = rm.positionWithOrderCode("l1hjqmus1f");
		rm.orders.get(position).addProducts("4898465132", 5);
		
	}
	@Test
	void testGetProduct() {
		setupScenary1();
		if (!rm.orders.isEmpty()) {
			for (int i = 0; i < rm.orders.size(); i++) {
				for (int j = 0; j < rm.orders.get(i).productsList.size(); j++) {
					assertNotNull(rm.orders.get(i).productsList.get(j).getProduct());
				}
			}
		}
	}
	@Test
	void testGetOrder() {
		setupScenary1();
		if (!rm.orders.isEmpty()) {
			for (int i = 0; i < rm.orders.size(); i++) {
				for (int j = 0; j < rm.orders.get(i).productsList.size(); j++) {
					assertNotNull(rm.orders.get(i).productsList.get(j).getOrder());
				}
			}
		}
	}

}
