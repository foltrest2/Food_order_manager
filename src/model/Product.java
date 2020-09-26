package model;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 1;
	private String name;
	private String code;
	private String description;
	private double cost;
	private String restaurantNit;
	private int quantity;

	/**
	 * This is the constructor of products
	 * 
	 * @param code is the code of the product
	 * @param name is the name of the product
	 * @param description is the description of the product
	 * @param cost is the cost of the product
	 */
	public Product(String name, String code, String description, double cost, String restaurantNit) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.cost = cost;
		this.restaurantNit = restaurantNit;
	}
	/**
	 * This is the overloaded constructor of products, create products for orders 
	 * @param code is the code of the product
	 * @param quantity is the quantity requested of the product 
	 */
	public Product(String code, int quantity) {
		this.code = code;
		this.quantity = quantity;
	}
	/**
	 * This method returns the code
	 * @return the existing code of product
	 */
	public String getCode() {
		return code;
	}
	/**
	 * This method replace the existing code for a new one
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * This method returns the name
	 * @return the existing name
	 */
	public String getName() {
		return name;
	}
	/**
	 * This method replace the existing name for a new one
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * This method returns the description
	 * @return the existing description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * This method replace the existing description for a new one
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * This method returns the cost
	 * @return the existing cost
	 */
	public double getCost() {
		return cost;
	}
	/**
	 * This method replace the existing cost for a new one
	 * @param cost
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}
	/**
	 * @return the restaurantNit
	 */
	public String getRestaurantNit() {
		return restaurantNit;
	}
	/**
	 * @param restaurantNit the restaurantNit to set
	 */
	public void setRestaurantNit(String restaurantNit) {
		this.restaurantNit = restaurantNit;
	}
	/**
	 * This method returns the quantity
	 * @return the existing quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * This method replace the existing quantity for a new one
	 * @param quantity is the quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * This method concatenate the general info of the product
	 * @return the info of the product concatenated
	 */
	public String getInfo() {
		String info = "";
		info += "Name: " + name + "\nCode: " + code + "\nDescription: " + description + "\nCost: " + cost + "\nRestaurant nit: " + restaurantNit + "\n" ;
		return info;
	}
	/**
	 * This method concatenate the info of the product in the order
	 * @return the info of the product concatenated
	 */
	public String getInfoWithQuantity() {
		String info = "";
		info += "Code: " + code + "\nQuantity: " + quantity + "\n";
		return info;
	}
	/**
	 * This method concatenate the info of the product in the order to export it
	 * @param separator is the separator chosen
	 * @return the info of the product concatenated
	 */
	public String getInfoWithQuantityToExport(String separator) {
		String info = "";
		info += code + separator + quantity;
		return info;
	}

}