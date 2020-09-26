package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Restaurant implements Serializable{

	private static final long serialVersionUID = 1;
	private String name;
	private String nit;
	private String manager;
	public ArrayList<Product> products;

	/**
	 * This is the constructor of restaurant
	 * @param name is the name
	 * @param nit is the nit
	 * @param manager is the name of the manager
	 */
	public Restaurant(String name, String nit, String manager) {
		this.name = name;
		this.nit = nit;
		this.manager = manager;
		products = new ArrayList<Product>();
	}
	/**
	 * This method concatenate the info of the products in the restaurant
	 * @return the info of the products concatenated
	 */
	public String showProducts() {
		String info = "";
		if (products.isEmpty()) {
			info = "\nThere no products in list\n";
		}
		else {
			info += "\n" + name + " has these products: \n";
			for (int i = 0; i < products.size(); i++) {
				info += "\n" + products.get(i).getInfo()+"This is the product #" + (i+1) + "\n";
			}	
		}
		return info;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}
	/**
	 * @param nit the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}
	/**
	 * @return the manager
	 */
	public String getManager() {
		return manager;
	}
	/**
	 * @param manager the manager to set
	 */
	public void setManager(String manager) {
		this.manager = manager;
	}
	/**
	 * @return the products
	 */
	public ArrayList<Product> getProducts() {
		return products;
	}
	/**
	 * @param products the products to set
	 */
	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}
	/**
	 * This method concatenate the info of the restaurant with the respective products
	 * @return the info of the restaurant and products concatenated
	 */
	public String getInfoWithProducts() {
		String info = "";
		info += "Name: " + name + "\nRestaurant nit: " + nit + "\nManager: " + manager + "\n";
		info += showProducts();
		return info;
	}
	/**
	 * This method concatenate the info of the restaurant
	 * @return the info of the restaurant concatenated
	 */
	public String getInfoWithoutProducts() {
		String info = "";
		info += "Name: " + name + "\nRestaurant nit: " + nit + "\nManager: " + manager + "\n";
		return info;
	}

}