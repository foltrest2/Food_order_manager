package model;

import java.io.Serializable;

public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String name;
	private String code;
	private String description;
	private double cost;
	private String restaurantNit;
	private int quantity;

	/**
	 * 
	 * @param code
	 * @param name
	 * @param description
	 * @param cost
	 */
	public Product(String name, String code, String description, double cost, String restaurantNit) {
		this.name = name;
		this.code = code;
		this.description = description;
		this.cost = cost;
		this.restaurantNit = restaurantNit;
	}

	public Product(String code, int quantity) {
		this.code = code;
		this.quantity = quantity;
	}
	
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
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

	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public double getCost() {
		return cost;
	}

	/**
	 * 
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getInfo() {
		String info = "";
		info += "Name: " + name + "\nCode: " + code + "\nDescription: " + description + "\nCost: " + cost + "\nRestaurant nit: " + restaurantNit + "\n" ;
		return info;
	}
	
	public String getInfoWithQuantity() {
		String info = "";
		info += "Code: " + code + "\nQuantity: " + quantity + "\n";
		return info;
	}

}