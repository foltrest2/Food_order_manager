package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String name;
	private String nit;
	private String manager;
	public ArrayList<Product> products;

	/**
	 * 
	 * @param name
	 * @param nit
	 * @param manager
	 */
	public Restaurant(String name, String nit, String manager) {
		this.name = name;
		this.nit = nit;
		this.manager = manager;
		products = new ArrayList<>();
	}

	public String showProducts() {
		String info = "";
		if (products.isEmpty()) {
			info = "\nThere no products in list\n";
		}
		else {
			info += name + " has these products: \n";
			for (int i = 0; i < products.size(); i++) {
				info += products.get(i).getInfo()+"This is the product #" + (i+1) + "\n";
			}	
		}
		return info;
	}
	
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getNit() {
		return nit;
	}

	/**
	 * 
	 * @param nit
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getManager() {
		return manager;
	}

	/**
	 * 
	 * @param manager
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}

	public String getInfo() {
		String info = "";
		info += "Name: " + name + "\nRestaurant nit: " + nit + "\nManager: " + manager + "\n";
		info += showProducts();
		return info;
	}

}