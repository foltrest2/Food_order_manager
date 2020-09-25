package model;

import java.io.Serializable;
import model.Product;
import model.Order;

public class ItemOrder implements Serializable{
	
	private static final long serialVersionUID = 1;
	private Product product;
	private Order order;

	public ItemOrder(Product p, Order o) {
		product = p;
		order = o;
	}

	public Product getProduct() {
		return product;
	}

	public Order getOrder() {
		return order;
	}
	
}
