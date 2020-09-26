package model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Order implements Serializable{

	private static final long serialVersionUID = 1;
	private String code;
	private String date;
	private String status;
	private String clientIdNum;
	private String restaurantNit;
	public enum status {REQUESTED, IN_PROCESS, SENT, DELIVERED}
	public status orderStat;
	public ArrayList<ItemOrder> productsList;
	/**
	 * This is the constructor of order
	 * @param code is the code
	 * @param clientIdNum is the client ID number
	 * @param restaurantNit is the restaurant nit
	 */
	@SuppressWarnings("static-access")
	public Order(String code, String clientIdNum, String restaurantNit) {
		this.code = code;
		this.date = "" + (LocalDate.now().getDayOfMonth()) + "/" + LocalDate.now().getMonthValue() + "/" + LocalDate.now().getYear();
		this.clientIdNum = clientIdNum;
		this.restaurantNit = restaurantNit;
		productsList = new ArrayList<>();
		this.status = orderStat.REQUESTED.name();
	}
	/**
	 * This is the overloaded constructor of order, used to import
	 * @param code is the code
	 * @param date is the date
	 * @param clientIdNum is the client ID number
	 * @param status is the status
	 * @param restaurantNit is the restaurant nit
	 */
	public Order(String code, String date, String clientIdNum, String status, String restaurantNit) {
		this.code = code;
		this.date = date;
		this.clientIdNum = clientIdNum;
		this.status = status;
		this.restaurantNit = restaurantNit;
		productsList = new ArrayList<>();
	}
/**
 * This method add the products to the order
 * @param code product code
 * @param quantity product quantity
 * @return a message saying what happens with the product
 * <br>Precondition:</br> Parameters received are not null
 */
	public String addProducts(String code, int quantity) {
		String info = "";
		Product p = new Product(code, quantity);
		boolean unique = uniqueProductCode(p.getCode());
		if (unique) {
			productsList.add(new ItemOrder(p,this));
			info += "Product added!";
		}
		else
			info += "The product it's already in the order, you can't add it again";
		
		return info;
	}
	/**
	 * This method check if the product code isn't duplicate
	 * @param code is product code
	 * @return a boolean with the result of the search
	 */
	public boolean uniqueProductCode(String code){
		boolean unique = true;
		for(int i=0; i<productsList.size() && unique; i++){
			if(productsList.get(i).getProduct().getCode().equalsIgnoreCase(code)){
				unique = false;
			}
		}
		return unique;
	}
	/**
	 * This method update the status of the order
	 * @return a message with the new status
	 */
	@SuppressWarnings("static-access")
	public String updateStatus() {
		String info = "";
		boolean changed = true;
		if (status.equals(orderStat.REQUESTED.name())&&changed) {
			status = orderStat.IN_PROCESS.name();
			info += "Status is now " + orderStat.IN_PROCESS.name() + "\n";
			changed = false;
		}
		if (status.equals(orderStat.IN_PROCESS.name())&&changed) {
			status = orderStat.SENT.name();
			info += "Status is now" + orderStat.SENT.name() + "\n";
			changed = false;
		}
		if (status.equals(orderStat.SENT.name())&&changed) {
			status = orderStat.DELIVERED.name();
			info += "Status is now" + orderStat.DELIVERED.name() + "\n";
			changed = false;
		}
		return info;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the clientIdNum
	 */
	public String getClientIdNum() {
		return clientIdNum;
	}
	/**
	 * @param clientIdNum the clientIdNum to set
	 */
	public void setClientIdNum(String clientIdNum) {
		this.clientIdNum = clientIdNum;
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
	 * @return the orderStat
	 */
	public status getOrderStat() {
		return orderStat;
	}
	/**
	 * @param orderStat the orderStat to set
	 */
	public void setOrderStat(status orderStat) {
		this.orderStat = orderStat;
	}
	/**
	 * @return the productsList
	 */
	public ArrayList<ItemOrder> getProductsList() {
		return productsList;
	}
	/**
	 * @param productsList the productsList to set
	 */
	public void setProductsList(ArrayList<ItemOrder> productsList) {
		this.productsList = productsList;
	}
	/**
	 * This method concatenate the info of the order
	 * @return the info of the order concatenated
	 */
	public String getInfo() {
		String info = "";
		info += "Code: " + code + "\nDate: " + date + "\nStatus: " + status + "\nClient ID: " + clientIdNum + "\nRestaurant nit: " + restaurantNit + "\n";
		return info;
	}
	/**
	 * This method concatenate the info of the product in the order
	 * @return the info of the product concatenated
	 */
	public String getProducts() {
		String info = "";
		if(!productsList.isEmpty()) {
			for (int i = 0; i < productsList.size(); i++) {
				info += productsList.get(i).getProduct().getInfoWithQuantity() + "\nThis is the product #" + (i+1) + "\n";
			}
		}
		else
			info += "No products in this order\n";
		return info;
	}
	
}